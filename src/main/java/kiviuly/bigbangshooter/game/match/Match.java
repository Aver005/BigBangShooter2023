package kiviuly.bigbangshooter.game.match;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.GameStage;
import kiviuly.bigbangshooter.game.GameStageStorage;
import kiviuly.bigbangshooter.game.user.Team;
import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.game.arena.Arena;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Match extends GameStage
{
    private static final GameStageStorage<Match> MatchStorage = new GameStageStorage<>();
    public static GameStageStorage<Match> getMatchStorage() {return MatchStorage;}

    private final MatchStatus status;
    private final ArrayList<Team> teams;
    private final HashMap<Team, ArrayList<User>> playersByTeam;

    public Match(Arena arena, ArrayList<User> players)
    {
        super(arena);
        status = MatchStatus.BUY_TIME;
        runnable = new MatchRunnable(this);
        teams = (ArrayList<Team>) List.of(Team.DEFENSE, Team.ATTACK);
        playersByTeam = new HashMap<>();

        for(User u : players) {Join(u);}
        runnable.Start();
        getMatchStorage().add(this);
    }

    public void SpawnUser(User u)
    {

    }

    public void AddSpectator(User u)
    {

    }

    @Override
    public void Join(User u)
    {
        if (players.size() >= arena.getMaxPlayers())
        {
            DataManager.SendMessageFromConfig(u.getPlayer(), "MatchIsFull");
            return;
        }

        if (players.contains(u))
        {
            DataManager.SendMessageFromConfig(u.getPlayer(), "UserAlreadyInThisMatch");
            return;
        }

        u.setGameStage(this);
        players.add(u);

        int size = arena.getMaxPlayers();
        Team choosedTeam = u.getTeam();
        ArrayList<User> teamPlayers = getPlayersByTeam(choosedTeam);

        if (choosedTeam.equals(Team.NONE))
        {
            for(Team team : teams)
            {
                if (playersByTeam.get(team).size() <= size)
                {
                    choosedTeam = team;
                    teamPlayers = playersByTeam.get(choosedTeam);
                    size = teamPlayers.size();
                }
            }

            if (choosedTeam.equals(Team.NONE))
            {
                choosedTeam = getRandomTeam();
                teamPlayers = playersByTeam.get(choosedTeam);
            }
        }

        teamPlayers.add(u);
        playersByTeam.put(choosedTeam, teamPlayers);

        if (status.equals(MatchStatus.BUY_TIME))
        {
            SpawnUser(u);
            return;
        }

        AddSpectator(u);
    }

    @Override
    public void Leave(User u)
    {
        if (!players.contains(u))
        {
            DataManager.SendMessageFromConfig(u.getPlayer(), "UserNotInThisMatch");
            return;
        }

        u.setGameStage(null);
        players.remove(u);

        // TODO: Обрабатывать выход игрока при разных состояниях матча
    }

    @Override
    public void Remove() {MatchStorage.remove(this);}

    @Override
    public void UserDamagedByUser(EntityDamageByEntityEvent e, User victim, User damager)
    {
        if (status.equals(MatchStatus.BUY_TIME))
        {
            e.setDamage(0);
            e.setCancelled(true);
        }
    }

    @Override
    public void UserDamaged(EntityDamageEvent e, User user)
    {
        if (status.equals(MatchStatus.BUY_TIME))
        {
            e.setDamage(0);
            e.setCancelled(true);
        }
    }

    public boolean isPlaying(User u) {return players.contains(u);}
    public MatchStatus getStatus() {return status;}
    public ArrayList<User> getPlayersByTeam(Team team) {return playersByTeam.getOrDefault(team, new ArrayList<>());}
    public Team getRandomTeam() {return (Team) playersByTeam.keySet().toArray()[DataManager.getRandom().nextInt(playersByTeam.size())];}
}
