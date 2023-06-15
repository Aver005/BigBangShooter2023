package kiviuly.bigbangshooter.game.user;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.Element;
import kiviuly.bigbangshooter.game.GameStage;
import kiviuly.bigbangshooter.game.arena.Arena;
import kiviuly.bigbangshooter.game.match.Unit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class User extends Element
{
    private final Player player;

    private final ArrayList<Unit> units;

    private Unit unit;
    private GameStage gameStage;
    private Arena editArena = null;
    private Team team = Team.NONE;

    private double lobbyDamage = 0.0f;
    private double absorbedDamage = 0.0f;

    private boolean isSpectating = false;

    public User(Player player)
    {
        this.player = player;
        this.units = new ArrayList<>();
        UserStorage.getInstance().Add(this);
    }

    @Override
    public void Save(YamlConfiguration config)
    {

    }

    @Override
    public void Load(YamlConfiguration config)
    {

    }

    public void SavePlayer()
    {
        File temp = new File(DataManager.getDataFolder() + File.separator + "Players");
        if (!temp.exists()) {temp.mkdir();}

        temp = new File(DataManager.getDataFolder() + File.separator + "Players" + File.separator + player.getUniqueId() + ".data");
        HashMap<String, Object> data = new HashMap<>();

        data.put("Location", player.getLocation());
        data.put("DisplayName", player.getDisplayName());
        data.put("InventoryContents", player.getInventory().getContents());
        data.put("ArmorContents", player.getInventory().getArmorContents());
        data.put("HP", player.getHealth());
        data.put("FOOD", player.getFoodLevel());
        data.put("isFlying", player.isFlying());
        data.put("GameMode", player.getGameMode());
        data.put("PotionEffects", player.getActivePotionEffects());
        data.put("LEVEL", player.getLevel());
        data.put("EXP", player.getExp());
        data.put("WalkSpeed", player.getWalkSpeed());
        data.put("FlySpeed", player.getFlySpeed());
        data.put("AllowFlight", player.getAllowFlight());

        try
        {
            ObjectOutputStream ois = new BukkitObjectOutputStream(Files.newOutputStream(temp.toPath()));
            ois.writeObject(data); ois.flush(); ois.close();
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public void LoadPlayer()
    {
        File temp = new File(DataManager.getDataFolder() + File.separator + "Players" + File.separator + player.getUniqueId() + ".data");
        if (!temp.exists()) {return;}

        try
        {
            ObjectInputStream ois = new BukkitObjectInputStream(Files.newInputStream(temp.toPath()));
            HashMap<String, Object> data = (HashMap<String, Object>) ois.readObject();
            ois.close();

            player.setDisplayName((String) data.getOrDefault("DisplayName", player.getDisplayName()));
            player.getInventory().setContents((ItemStack[]) data.getOrDefault("InventoryContents", player.getInventory().getContents()));
            player.getInventory().setArmorContents((ItemStack[]) data.getOrDefault("ArmorContents", player.getInventory().getArmorContents()));
            player.setHealth((double) data.getOrDefault("HP", player.getHealth()));
            player.setFoodLevel((int) data.getOrDefault("FOOD", player.getFoodLevel()));
            player.setGameMode((GameMode) data.getOrDefault("GameMode", player.getGameMode()));
            player.addPotionEffects((Collection<PotionEffect>) data.getOrDefault("PotionEffects", player.getActivePotionEffects()));
            player.setLevel((int) data.getOrDefault("LEVEL", player.getLevel()));
            player.setExp((float) data.getOrDefault("EXP", player.getExp()));
            player.setWalkSpeed((float) data.getOrDefault("WalkSpeed", player.getWalkSpeed()));
            player.setFlySpeed((float) data.getOrDefault("FlySpeed", player.getFlySpeed()));
            player.teleport((Location) data.getOrDefault("Location", player.getBedSpawnLocation()));
            player.setAllowFlight((boolean) data.getOrDefault("AllowFlight", player.getAllowFlight()));
            if (player.getAllowFlight()) {player.setFlying((boolean) data.getOrDefault("isFlying", player.isFlying()));}
        }
        catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
    }

    public void ClearPlayer()
    {
        player.setDisplayName(player.getName());
        player.getInventory().setArmorContents(null);
        player.getInventory().clear();
        player.setMaxHealth(20);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.ADVENTURE);
        player.setLevel(60);
        player.setExp(0);
        player.setFlying(false);
        player.setWalkSpeed(0.2F);
        player.setFlySpeed(0.2F);
        player.closeInventory();
        for(PotionEffect pi : player.getActivePotionEffects()) {player.removePotionEffect(pi.getType());}
    }

    public double getLobbyDamage() {return lobbyDamage;}
    public void addLobbyDamage(double damage) {lobbyDamage += damage;}
    public void setLobbyDamage(double damage) {lobbyDamage = damage;}

    public double getAbsorbedDamage() {return absorbedDamage;}
    public void addAbsorbedDamage(double damage) {absorbedDamage += damage;}
    public void setAbsorbedDamage(double damage) {absorbedDamage = damage;}

    public Unit getUnit() {return unit;}
    public void setUnit(Unit unit) {this.unit = unit;}

    public Player getPlayer() {return player;}
    public String getName() {return player.getName();}
    public Team getTeam() {return team;}
    public void setTeam(Team team) {this.team = team;}
    public GameStage getGameStage() {return gameStage;}
    public void setGameStage(GameStage gameStage) {this.gameStage = gameStage;}
    public boolean isSpectating() {return isSpectating;}
    public void setSpectating(boolean isSpectating) {this.isSpectating = isSpectating;}
    public Arena getEditArena() {return editArena;}
    public void setEditArena(Arena editArena) {this.editArena = editArena;}
    public boolean isEditing() {return editArena != null;}

    @Override
    public String getID() {return player.getName();}
}