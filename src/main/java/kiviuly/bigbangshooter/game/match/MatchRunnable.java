package kiviuly.bigbangshooter.game.match;

import kiviuly.bigbangshooter.game.arena.Arena;

public class MatchRunnable implements Runnable
{
    private Match match;
    private Arena arena;
    private int seconds = 0;

    public MatchRunnable(Match match)
    {
        this.match = match;
        this.arena = match.getArena();
    }

    @Override
    public void run()
    {

    }
}
