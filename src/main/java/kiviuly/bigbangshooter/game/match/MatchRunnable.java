package kiviuly.bigbangshooter.game.match;

import kiviuly.bigbangshooter.game.GameStageRunnable;

public class MatchRunnable extends GameStageRunnable
{
    private Match match;

    public MatchRunnable(Match match)
    {
        super(match);
        this.match = match;
    }

    @Override
    public void run()
    {
        seconds++;
    }
}
