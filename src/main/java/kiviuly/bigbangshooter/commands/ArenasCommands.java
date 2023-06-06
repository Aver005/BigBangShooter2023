package kiviuly.bigbangshooter.commands;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.arena.Arena;
import kiviuly.bigbangshooter.game.arena.ArenaStorage;
import kiviuly.bigbangshooter.game.arena.EditMode;
import org.bukkit.entity.Player;

public class ArenasCommands extends Commands
{
    public ArenasCommands(Player p, String[] args) {super(p, args);}


    public void Create(String arenaID)
    {
        if (ArenaStorage.isExists(arenaID))
        {
            DataManager.SendMessageFromConfig(getPlayer(), "ArenaExists");
            return;
        }

        Arena arena = new Arena(arenaID);
        EditMode.GiveEditItems(arena, getPlayer());
    }

    public void Edit(String arenaID)
    {
        if (!ArenaStorage.isExists(arenaID))
        {
            DataManager.SendMessageFromConfig(getPlayer(), "ArenaNotExists");
            return;
        }

        Arena arena = ArenaStorage.get(arenaID);
        EditMode.GiveEditItems(arena, getPlayer());
    }
}