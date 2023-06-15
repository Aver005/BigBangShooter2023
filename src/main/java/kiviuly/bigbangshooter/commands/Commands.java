package kiviuly.bigbangshooter.commands;

import kiviuly.bigbangshooter.game.user.User;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Commands
{
    private final User user;
    private final String[] args;

    public Commands(User user, String[] args)
    {
        this.user = user;
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

    public User getUser() {return user;}
    public Player getPlayer() {return user.getPlayer();}
    public String[] getArgs() {return args;}
}
