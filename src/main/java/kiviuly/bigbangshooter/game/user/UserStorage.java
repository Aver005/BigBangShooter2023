package kiviuly.bigbangshooter.game.user;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class UserStorage
{
    private static HashMap<String, User> users = new HashMap<>();


    public static void add(User user) {users.put(user.getName(), user);}

    public static User get(Player pl)
    {
        if (users.containsKey(pl.getName())) {return users.get(pl.getName());}
        return new User(pl);
    }
}