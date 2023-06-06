package kiviuly.bigbangshooter.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandsHandler implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args)
    {
        if (!(sender instanceof Player)) {return true;}

        Player p = (Player) sender;
        int count = args.length;

        if (count == 0)
        {
            return true;
        }

        String sub = args[0].toLowerCase();

        if (sub.equals("arena"))
        {
            new ArenasCommands(p, args);
            return true;
        }

        if (sub.equals("op") || sub.equals("operator"))
        {
            new OperatorCommands(p, args);
            return true;
        }

        return true;
    }
}