package kiviuly.bigbangshooter.game.match;

import kiviuly.bigbangshooter.game.user.Operator;
import kiviuly.bigbangshooter.game.user.User;
import org.bukkit.event.player.PlayerInteractEvent;

public class Unit
{
    private final User owner;
    private final Operator operator;
    private final int cost;

    public Unit(User owner, Operator operator)
    {
        this.owner = owner;
        this.operator = operator;
        this.cost = operator.getSellPrice();
    }

    public void InteractItem(PlayerInteractEvent e)
    {

    }
}