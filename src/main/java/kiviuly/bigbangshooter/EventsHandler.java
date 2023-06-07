package kiviuly.bigbangshooter;

import kiviuly.bigbangshooter.game.GameStage;
import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.game.user.UserStorage;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;

public class EventsHandler implements Listener
{
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        User u = UserStorage.get(p);
    }

    @EventHandler
    public void OnPlayerUseItemStack(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();
        User u = UserStorage.get(p);
        ItemStack is = p.getItemInUse();

        /*
            TODO: Возможно стоит подумать над тем
                  Что у каждого GameStage есть своё меню
                  У Match - выбор опера
                  У Lobby - выбор команды

        */
    }

    @EventHandler
    public void OnBlockBreak(BlockBreakEvent e)
    {
        User u = UserStorage.get(e.getPlayer());
        if (u.getGameStage() == null) {return;}

        e.setCancelled(true);
    }

    @EventHandler
    public void OnBlockPlace(BlockPlaceEvent e)
    {
        User u = UserStorage.get(e.getPlayer());
        if (u.getGameStage() == null) {return;}

        e.setCancelled(true);
    }

    @EventHandler
    public void OnPlayerDamage(EntityDamageByEntityEvent e)
    {
        if (!e.getEntityType().equals(EntityType.PLAYER)) {return;}

        User victim = UserStorage.get((Player) e.getEntity());
        GameStage stage = victim.getGameStage();
        if (stage == null) {return;}

        if (e.getDamager().getType().equals(EntityType.PLAYER))
        {
            User damager = UserStorage.get((Player) e.getDamager());

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

        User u = UserStorage.get((Player) e.getEntity());
        GameStage stage = u.getGameStage();
        if (stage == null) {return;}

        stage.UserDamaged(e, u);
    }
}