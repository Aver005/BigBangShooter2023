package kiviuly.bigbangshooter.game.lobby;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.GameStage;
import kiviuly.bigbangshooter.game.GameStageRunnable;
import kiviuly.bigbangshooter.game.match.Match;

import java.util.HashMap;

public class LobbyRunnable extends GameStageRunnable
{
    public LobbyRunnable(GameStage gameStage) {super(gameStage);}

    @Override
    public void run()
    {
        if (seconds == arena.getLobbyTimeToStart())
        {
            new Match(arena, gameStage.getPlayers());
            Stop();
            return;
        }

        if (seconds % 10 == 0 || seconds < 6)
        {
            HashMap<String, String> placeholders = new HashMap<>();
            placeholders.put("seconds", seconds + "");
            String header = DataManager.getMessage("LobbyStartInTitleHeader", placeholders);
            String footer = DataManager.getMessage("LobbyStartInTitleFooter", placeholders);
            gameStage.SendTitle(header, footer);
        }

        seconds++;
    }
}
