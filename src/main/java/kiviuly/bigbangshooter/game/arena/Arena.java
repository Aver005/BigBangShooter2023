package kiviuly.bigbangshooter.game.arena;

import kiviuly.bigbangshooter.DataManager;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;

public class Arena
{
    private final String ID;
    private final String name;
    private final ArenaState state;

    private final int buyTime = 5;
    private final int plantTime = 5;
    private final int defuseTime = 12;
    private final int roundTime = 120;
    private final int endRoundTime = 4;

    private final ArrayList<Location> defenseSpawns;
    private final ArrayList<Location> attackSpawns;
    private final Location lobby;

    private final HashMap<String, Material> plantBlockBySiteName;
    private final Material bombMaterial;

    public Arena(String ID)
    {
        this.ID = ID;
        this.name = ID;
        this.state = ArenaState.DISABLED;

        this.defenseSpawns = new ArrayList<>();
        this.attackSpawns = new ArrayList<>();

        this.bombMaterial = Material.OBSIDIAN;
        this.plantBlockBySiteName = new HashMap<>();
        this.plantBlockBySiteName.put("A", Material.PURPLE_CONCRETE_POWDER);
        this.plantBlockBySiteName.put("B", Material.RED_CONCRETE_POWDER);

        this.lobby = null;
        ArenaStorage.add(this);
    }

    public String getID() {return ID;}
    public String getName() {return name;}
    public Material getBombMaterial() {return bombMaterial;}
    public Location getLobby() {return lobby;}
    public int getBuyTime() {return buyTime;}
    public int getPlantTime() {return plantTime;}
    public int getDefuseTime() {return defuseTime;}
    public int getRoundTime() {return roundTime;}
    public int getEndRoundTime() {return endRoundTime;}
}
