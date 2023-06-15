package kiviuly.bigbangshooter.game.lobby;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.GameStage;
import kiviuly.bigbangshooter.game.GameStageStorage;
import kiviuly.bigbangshooter.game.arena.Arena;
import kiviuly.bigbangshooter.game.match.MatchItem;
import kiviuly.bigbangshooter.game.user.User;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class Lobby extends GameStage
{
    private static final GameStageStorage<Lobby> LobbyStorage = new GameStageStorage<>();
    public static GameStageStorage<Lobby> getLobbyStorage() {return LobbyStorage;}

    public Lobby(Arena arena)
    {
        super(arena);
        this.runnable = new LobbyRunnable(this);
        getLobbyStorage().add(this);
    }

    public void GiveItems(User u)
    {
        for(LobbyItem item : LobbyItem.values()) {item.Give(u);}
    }

    @Override
    public void Join(User u)
    {
        if (players.contains(u))
        {
            DataManager.SendMessageFromConfig(u.getPlayer(), "UserAlreadyInThisLobby");
            return;
        }

        players.add(u);
        u.setGameStage(this);
        u.SavePlayer();
        u.ClearPlayer();
        u.getPlayer().teleport(arena.getLobby());
        GiveItems(u);

        if (players.size() < arena.getMinPlayers()) {return;}
        runnable.Start();
    }

    @Override
    public void Leave(User u)
    {
        if (!players.contains(u))
        {
            DataManager.SendMessageFromConfig(u.getPlayer(), "UserNotInThisLobby");
            return;
        }

        players.remove(u);
        u.setGameStage(null);
        u.ClearPlayer();
        u.LoadPlayer();

        if (players.size() >= arena.getMinPlayers()) {return;}
        runnable.Stop();
    }

    @Override
    public void Remove() {LobbyStorage.remove(this);}

    @Override
    public void UserInteractItem(PlayerInteractEvent e, User user)
    {
        ItemStack is = e.getItem();
        if (is == null) {e.setCancelled(true); return;}
        if (is.equals(LobbyItem.EXIT.getItem())) {Leave(user); return;}
        if (is.equals(LobbyItem.TEAM.getItem())) {return;}
        if (is.equals(LobbyItem.OPERATOR.getItem())) {return;}
        e.setCancelled(true);
    }

    @Override
    public void UserJoinServer(PlayerJoinEvent e, User user)
    {

    }

    @Override
    public void UserLeaveServer(PlayerQuitEvent e, User user)
    {
        Leave(user);
    }

    @Override
    public void UserPlaceBlock(BlockPlaceEvent e, User user)
    {
        e.setCancelled(true);
    }

    @Override
    public void UserBreakBlock(BlockBreakEvent e, User user)
    {
        e.setCancelled(true);
    }

    @Override
    public void UserDamagedByUser(EntityDamageByEntityEvent e, User victim, User damager)
    {
        damager.addLobbyDamage(e.getFinalDamage());
        victim.addAbsorbedDamage(e.getFinalDamage());
        e.setDamage(0);
    }

    @Override
    public void UserDamaged(EntityDamageEvent e, User user) {e.setCancelled(true);}
}
