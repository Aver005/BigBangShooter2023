package kiviuly.bigbangshooter.commands;

import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Commands
{
    private final Player player;
    private final String[] args;

    public Commands(Player p, String[] args)
    {
        this.player = p;
        this.args = args;

        String action = args[1].substring(0, 1).toUpperCase() + args[1].substring(1).toLowerCase();
        String name = args[2];

        try
        {
            Method method = this.getClass().getMethod(action, String.class);
            method.invoke(this, name);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {return player;}
    public String[] getArgs() {return args;}
}
