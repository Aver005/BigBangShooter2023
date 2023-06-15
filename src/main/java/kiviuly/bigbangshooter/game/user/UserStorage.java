package kiviuly.bigbangshooter.game.user;

import kiviuly.bigbangshooter.game.Storage;
import org.bukkit.Bukkit;

public class UserStorage extends Storage<User, String>
{
    private static UserStorage instance;
    public static UserStorage getInstance() {return instance;}

    public UserStorage(String folderName)
    {
        super(folderName);
        instance = this;
    }

    @Override
    public void Add(User user) {getStorage().put(user.getName(), user);}

    @Override
    public User Get(String plName)
    {
        if (getStorage().containsKey(plName)) {return getStorage().get(plName);}
        return new User(Bukkit.getPlayer(plName));
    }

    @Override
    public void Load()
    {

    }
}