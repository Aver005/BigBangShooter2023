package kiviuly.bigbangshooter;

import kiviuly.bigbangshooter.commands.CommandsHandler;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        DataManager.setDataFolder(getDataFolder().getAbsolutePath());
        DataManager.LoadMessages();

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
