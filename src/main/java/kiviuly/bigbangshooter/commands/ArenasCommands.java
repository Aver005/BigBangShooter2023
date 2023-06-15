package kiviuly.bigbangshooter.commands;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.arena.Arena;
import kiviuly.bigbangshooter.game.arena.ArenaState;
import kiviuly.bigbangshooter.game.arena.ArenaStorage;
import kiviuly.bigbangshooter.game.arena.EditItemStorage;
import kiviuly.bigbangshooter.game.user.Team;
import kiviuly.bigbangshooter.game.user.User;

public class ArenasCommands extends Commands
{
    private final ArenaStorage storage;
    public ArenasCommands(User user, String[] args)
    {
        super(user, args);
        storage = ArenaStorage.getInstance();
    }


    public void Create(String arenaID)
    {
        if (storage.IsExists(arenaID))
        {
            DataManager.SendMessageFromConfig(getPlayer(), "ArenaExists");
            return;
        }

        if (getUser().isEditing()) {EditItemStorage.RemoveItemsFromUser(getUser());}
        Arena arena = new Arena(arenaID);
        getUser().setEditArena(arena);
        EditItemStorage.GenerateItemsForArena(arena);
        EditItemStorage.GiveItemsToUser(getUser());
        DataManager.SendMessageFromConfig(getPlayer(), "ArenaCreated");
    }

    public void Edit(String arenaID)
    {
        if (!storage.IsExists(arenaID))
        {
            DataManager.SendMessageFromConfig(getPlayer(), "ArenaNotExists");
            return;
        }

        if (getUser().isEditing()) {EditItemStorage.RemoveItemsFromUser(getUser());}
        Arena arena = storage.Get(arenaID);
        getUser().setEditArena(arena);
        EditItemStorage.GiveItemsToUser(getUser());
        DataManager.SendMessageFromConfig(getPlayer(), "EditItemsGiven");
    }

    public void Lobby(String arenaID)
    {
        if (!storage.IsExists(arenaID))
        {
            DataManager.SendMessageFromConfig(getPlayer(), "ArenaNotExists");
            return;
        }

        Arena arena = storage.Get(arenaID);
        arena.setLobby(getPlayer().getLocation());
        DataManager.SendMessageFromConfig(getPlayer(), "ArenaLobbySet");
    }

    public void Enable(String arenaID)
    {
        if (!storage.IsExists(arenaID))
        {
            DataManager.SendMessageFromConfig(getPlayer(), "ArenaNotExists");
            return;
        }

        Arena arena = storage.Get(arenaID);

        if (arena.getLobby() == null)
        {
            arena.setState(ArenaState.LOBBY_NOT_SET);
            DataManager.SendMessageFromConfig(getPlayer(), "ArenaNeedLobbySet");
            return;
        }

        for(Team team : Team.values())
        {
            if (arena.getSpawns(team).size() == 0)
            {
                arena.setState(ArenaState.TEAM_SPAWNS_EMPTY);
                DataManager.SendMessageFromConfig(getPlayer(), "ArenaNeedTeamSpawns");
                return;
            }
        }

        arena.setState(ArenaState.ENABLED);
        DataManager.SendMessageFromConfig(getPlayer(), "ArenaLobbySet");
    }
}