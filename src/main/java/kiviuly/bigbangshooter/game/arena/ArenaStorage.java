package kiviuly.bigbangshooter.game.arena;

import kiviuly.bigbangshooter.game.Storage;

public class ArenaStorage extends Storage<Arena, String>
{
    private static ArenaStorage instance;
    public static ArenaStorage getInstance() {return instance;}

    public ArenaStorage(String folderName)
    {
        super(folderName);
        instance = this;
    }

    @Override
    public void Add(Arena arena) {getStorage().put(arena.getID(), arena);}
    @Override
    public Arena Get(String ID) {return getStorage().getOrDefault(ID, null);}

    @Override
    public void Load()
    {

    }
}