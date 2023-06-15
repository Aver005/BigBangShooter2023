package kiviuly.bigbangshooter;

import kiviuly.bigbangshooter.commands.CommandsHandler;
import kiviuly.bigbangshooter.game.Storage;
import kiviuly.bigbangshooter.game.arena.ArenaStorage;
import kiviuly.bigbangshooter.game.user.OperatorStorage;
import kiviuly.bigbangshooter.game.user.UserStorage;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin
{
    private static Main Instance;
    public static Main getInstance() {return Instance;}

    private ArrayList<Storage<? extends Element, ?>> storages = new ArrayList<>();

    @Override
    public void onEnable()
    {
        Instance = this;
        saveResource("Messages.yml", false);
        saveResource("Items.yml", false);
        getServer().getPluginManager().registerEvents(new EventsHandler(), this);

        PluginDescriptionFile descriptionFile = getDescription();
        for(String cmdName : descriptionFile.getCommands().keySet())
        {
            PluginCommand cmd = getCommand(cmdName);
            cmd.setExecutor(new CommandsHandler());
            cmd.setAliases((List<String>) descriptionFile.getCommands().get("aliases"));
        }

        storages.add(new ArenaStorage("Arenas"));
        storages.add(new UserStorage("Users"));
        storages.add(new OperatorStorage("Operators"));
    }

    @Override
    public void onDisable()
    {
        for(Storage<? extends Element, ?> storage : storages)
            storage.Save();
    }
}
