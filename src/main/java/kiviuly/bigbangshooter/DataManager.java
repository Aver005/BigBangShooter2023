package kiviuly.bigbangshooter;

import kiviuly.bigbangshooter.game.arena.Arena;
import kiviuly.bigbangshooter.game.match.Match;
import kiviuly.bigbangshooter.game.match.MatchStatus;
import kiviuly.bigbangshooter.game.user.User;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DataManager
{
    private static String DataFolder;
    public static void setDataFolder(String dataFolder) {DataFolder = dataFolder;}
    private static HashMap<String, String> messages = new HashMap<>();

    public static void SendMessageFromConfig(Player p, String messageID)
    {
        p.sendMessage(messages.getOrDefault(messageID, "§cСообщение не найдено"));
    }

    public static void LoadMessages()
    {
        File file = new File(DataFolder + "messages.yml");
        if (!file.exists()) {try {file.createNewFile();} catch (IOException e) {throw new RuntimeException(e);}}
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section = config.getConfigurationSection("Messages");
        for (String key : section.getKeys(false)) {messages.put(key, section.getString(key));}
    }
}