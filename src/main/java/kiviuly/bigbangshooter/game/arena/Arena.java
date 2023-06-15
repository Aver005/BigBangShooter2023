package kiviuly.bigbangshooter.game.arena;

import kiviuly.bigbangshooter.Element;
import kiviuly.bigbangshooter.game.user.Team;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.HashMap;

public class Arena extends Element
{
    private final String ID;
    private String name;
    private ArenaState state;

    private int minPlayers = 2;
    private int maxPlayers = 32;

    private int lobbyTimeToStart = 10;
    private int buyTime = 5;
    private int plantTime = 5;
    private int defuseTime = 12;
    private int roundTime = 120;
    private int endRoundTime = 4;

    private Location lobby;
    private final HashMap<Team, ArrayList<Location>> spawnsByTeam;

    private final HashMap<String, Material> plantBlockBySiteName;
    private Material bombMaterial;

    public Arena(String ID)
    {
        this.ID = ID;
        this.name = ID;
        this.state = ArenaState.DISABLED;
        this.lobby = null;

        this.spawnsByTeam = new HashMap<>();
        for(Team team : Team.values()) {this.spawnsByTeam.put(team, new ArrayList<>());}

        this.bombMaterial = Material.OBSIDIAN;
        this.plantBlockBySiteName = new HashMap<>();
        this.plantBlockBySiteName.put("A", Material.PURPLE_CONCRETE_POWDER);
        this.plantBlockBySiteName.put("B", Material.RED_CONCRETE_POWDER);

        ArenaStorage.getInstance().Add(this);
    }

    public void addSpawn(Team team, Location spawn)
    {
        ArrayList<Location> spawns = spawnsByTeam.get(team);
        spawns.add(spawn);
        spawnsByTeam.put(team, spawns);
    }

    @Override
    public void Save(YamlConfiguration config)
    {

    }

    @Override
    public void Load(YamlConfiguration config)
    {

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
    public int getLobbyTimeToStart() {return lobbyTimeToStart;}
    public int getMaxPlayers() {return maxPlayers;}
    public int getMinPlayers() {return minPlayers;}

    public void setLobby(Location lobby) {this.lobby = lobby;}
    public void setState(ArenaState state) {this.state = state;}

    public ArrayList<Location> getSpawns(Team team) {return spawnsByTeam.getOrDefault(team, new ArrayList<>());}
}
