package kiviuly.bigbangshooter.game.match;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.game.arena.Arena;

import java.util.ArrayList;
import java.util.UUID;

public class Match
{
    private final UUID ID;
    private final Arena arena;
    private final ArrayList<User> players;
    private final MatchStatus status;

    private final MatchRunnable runnable;

    public Match(Arena arena)
    {
        this.arena = arena;
        this.ID = UUID.randomUUID();
        this.status = MatchStatus.LOBBY;
        this.players = new ArrayList<>();
        this.runnable = new MatchRunnable(this);
        MatchStorage.add(this);
    }

    public UUID getID() {return ID;}
    public Arena getArena() {return arena;}
    public MatchStatus getStatus() {return status;}
}
