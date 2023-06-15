package kiviuly.bigbangshooter.commands;

import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.game.user.UserStorage;
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
        User user = UserStorage.getInstance().Get(p.getName());
        int count = args.length;

        if (count == 0)
        {
            return true;
        }

        String sub = args[0].toLowerCase();

        if (sub.equals("arena"))
        {
            new ArenasCommands(user, args);
            return true;
        }

        if (sub.equals("op") || sub.equals("operator"))
        {
            new OperatorCommands(user, args);
            return true;
        }

        return true;
    }
}