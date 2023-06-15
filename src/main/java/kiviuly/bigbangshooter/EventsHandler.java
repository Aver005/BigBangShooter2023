package kiviuly.bigbangshooter;

import kiviuly.bigbangshooter.game.GameStage;
import kiviuly.bigbangshooter.game.arena.EditItem;
import kiviuly.bigbangshooter.game.arena.EditItemStorage;
import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.game.user.UserStorage;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class EventsHandler implements Listener
{
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        User u = UserStorage.getInstance().Get(p.getName());
        if (u.getGameStage() == null) {return;}
        u.getGameStage().UserJoinServer(e, u);
    }

    @EventHandler
    public void OnPlayerQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        User u = UserStorage.getInstance().Get(p.getName());
        if (u.getGameStage() == null) {return;}
        u.getGameStage().UserLeaveServer(e, u);
    }

    @EventHandler
    public void OnPlayerUseItemStack(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        User u = UserStorage.getInstance().Get(p.getName());

        if (u.isEditing())
        {
            ItemStack is = e.getItem();
            if (!EditItemStorage.IsEditItem(is, u.getEditArena())) {return;}
            EditItemStorage.GetEditByItem(is).onUse(u, e);
            return;
        }

        if (u.getGameStage() == null) {return;}
        u.getGameStage().UserInteractItem(e, u);
    }

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e)
    {
        User u = UserStorage.getInstance().Get(e.getPlayer().getName());
        if (u.getGameStage() == null) {return;}
        u.getGameStage().UserBreakBlock(e, u);
    }

    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent e)
    {
        Player p = e.getPlayer();
        User u = UserStorage.getInstance().Get(p.getName());

        if (u.isEditing())
        {
            ItemStack is = p.getItemInUse();
            if (!EditItemStorage.IsEditItem(is, u.getEditArena())) {return;}
            EditItemStorage.GetEditByItem(is).onPlace(u, e);
            return;
        }

        if (u.getGameStage() == null) {return;}
        u.getGameStage().UserPlaceBlock(e, u);
    }

    @EventHandler
    public void OnPlayerDamage(EntityDamageByEntityEvent e)
    {
        if (!e.getEntityType().equals(EntityType.PLAYER)) {return;}

        User victim = UserStorage.getInstance().Get(((Player) e.getEntity()).getName());
        GameStage stage = victim.getGameStage();
        if (stage == null) {return;}

        if (e.getDamager().getType().equals(EntityType.PLAYER))
        {
            User damager = UserStorage.getInstance().Get(((Player) e.getDamager()).getName());

            if (stage.equals(damager.getGameStage()))
            {
                stage.UserDamagedByUser(e, victim, damager);
                return;
            }
        }

        stage.UserDamaged(e, victim);
    }

    @EventHandler
    public void OnPlayerGetDamage(EntityDamageEvent e)
    {
        if (!e.getEntityType().equals(EntityType.PLAYER)) {return;}
        if (e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {return;}

        User u = UserStorage.getInstance().Get(((Player) e.getEntity()).getName());
        GameStage stage = u.getGameStage();
        if (stage == null) {return;}

        stage.UserDamaged(e, u);
    }
}