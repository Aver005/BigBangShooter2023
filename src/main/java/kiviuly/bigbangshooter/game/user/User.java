package kiviuly.bigbangshooter.game.user;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.match.Unit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class User
{
    private final Player player;

    private final ArrayList<Unit> units;
    private final Unit unit;

    public User(Player player)
    {
        this.player = player;
        this.units = new ArrayList<>();
        this.unit = null;
        UserStorage.add(this);
    }

    public Player getPlayer() {return player;}
    public String getName() {return player.getName();}
}