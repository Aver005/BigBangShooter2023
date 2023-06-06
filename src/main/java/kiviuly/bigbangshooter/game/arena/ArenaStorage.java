package kiviuly.bigbangshooter.game.arena;

import java.util.HashMap;

public class ArenaStorage
{
    private static HashMap<String, Arena> arenas = new HashMap<>();


    public static void add(Arena arena) {arenas.put(arena.getID(), arena);}
    public static Arena get(String ID) {return arenas.getOrDefault(ID, null);}
    public static boolean isExists(String ID) {return arenas.containsKey(ID);}
}