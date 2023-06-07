package kiviuly.bigbangshooter.game.lobby;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.GameStage;
import kiviuly.bigbangshooter.game.GameStageStorage;
import kiviuly.bigbangshooter.game.arena.Arena;
import kiviuly.bigbangshooter.game.user.User;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;

public class Lobby extends GameStage
{
    private static final GameStageStorage<Lobby> LobbyStorage = new GameStageStorage<>();
    public static GameStageStorage<Lobby> getLobbyStorage() {return LobbyStorage;}

    public Lobby(Arena arena)
    {
        super(arena);
        this.runnable = new LobbyRunnable(this);

        this.toolBarItems.add(LobbyItems.GetChooseTeamItem());
        this.toolBarItems.add(LobbyItems.GetExitItem());

        getLobbyStorage().add(this);
    }

    public void GiveItems(User u)
    {
        u.getPlayer().getInventory().setItem(0, LobbyItems.GetChooseTeamItem());
        u.getPlayer().getInventory().setItem(8, LobbyItems.GetExitItem());
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
    public void UserDamagedByUser(EntityDamageByEntityEvent e, User victim, User damager)
    {
        damager.addLobbyDamage(e.getFinalDamage());
        victim.addAbsorbedDamage(e.getFinalDamage());
        e.setDamage(0);
    }

    @Override
    public void UserDamaged(EntityDamageEvent e, User user) {e.setCancelled(true);}
}
