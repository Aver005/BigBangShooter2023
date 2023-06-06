package kiviuly.bigbangshooter;

import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.game.user.UserStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;

public class EventsHandler implements Listener
{
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        User u = UserStorage.get(p);
    }
}