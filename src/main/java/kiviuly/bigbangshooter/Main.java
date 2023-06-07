package kiviuly.bigbangshooter;

import kiviuly.bigbangshooter.commands.CommandsHandler;
import kiviuly.bigbangshooter.game.lobby.LobbyItems;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin
{
    private static Main Instance;
    public static Main getInstance() {return Instance;}

    @Override
    public void onEnable()
    {
        Instance = this;

        DataManager.setDataFolder(getDataFolder().getAbsolutePath());
        DataManager.LoadMessages();
        LobbyItems.Load();

        getServer().getPluginManager().registerEvents(new EventsHandler(), this);

        PluginCommand cmd = getCommand("bigbangshooter");
        cmd.setExecutor(new CommandsHandler());
        cmd.setAliases(new ArrayList<>(List.of("bbs")));
    }

    @Override
    public void onDisable()
    {

    }
}
