package kiviuly.bigbangshooter.game;

import kiviuly.bigbangshooter.Main;
import kiviuly.bigbangshooter.game.arena.Arena;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class GameStageRunnable extends BukkitRunnable
{
    protected GameStage gameStage;
    protected Arena arena;
    protected int seconds = 1;
    protected boolean isStarted = false;

    public GameStageRunnable(GameStage gameStage)
    {
        this.gameStage = gameStage;
        this.arena = gameStage.getArena();
    }

    public void Start()
    {
        if (isStarted) {return;}
        seconds = 1;
        isStarted = true;
        runTaskTimer(Main.getInstance(), 20L, 20L);
    }

    public void Stop()
    {
        if (!isStarted) {return;}
        isStarted = false;
        cancel();
    }
}