package kiviuly.bigbangshooter.game;

import kiviuly.bigbangshooter.game.arena.Arena;
import kiviuly.bigbangshooter.game.user.User;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public abstract class GameStage
{
    protected UUID ID;
    protected Arena arena;
    protected ArrayList<User> players;
    protected GameStageRunnable runnable;

    protected ArrayList<ItemStack> actionItems;


    public GameStage(Arena arena)
    {
        this.ID = UUID.randomUUID();
        this.arena = arena;
        this.players = new ArrayList<>();
        this.actionItems = new ArrayList<>();
    }


    public UUID getID() {return ID;}
    public Arena getArena() {return arena;}
    public ArrayList<User> getPlayers() {return players;}
    public ArrayList<ItemStack> getActionItems() {return actionItems;}
    public boolean isPlaying(User u) {return players.contains(u);}


    public void SendTitle(String header, String footer)
    {
        for(User u : players)
        {
            u.getPlayer().sendTitle(header, footer, 20, 30, 10);
        }
    }

    public void SendMessage(String message)
    {
        for(User u : players)
        {
            u.getPlayer().sendMessage(message);
        }
    }

    public abstract void Join(User u);
    public abstract void Leave(User u);
    public abstract void Remove();

    public abstract void UserDamagedByUser(EntityDamageByEntityEvent e, User victim, User damager);
    public abstract void UserDamaged(EntityDamageEvent e, User user);
    public abstract void UserInteractItem(PlayerInteractEvent e, User user);
    public abstract void UserJoinServer(PlayerJoinEvent e, User user);
    public abstract void UserLeaveServer(PlayerQuitEvent e, User user);
    public abstract void UserPlaceBlock(BlockPlaceEvent e, User user);
    public abstract void UserBreakBlock(BlockBreakEvent e, User user);
}