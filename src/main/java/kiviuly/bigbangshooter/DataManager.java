package kiviuly.bigbangshooter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class DataManager
{
    private static String DataFolder;
    public static void setDataFolder(String dataFolder) {DataFolder = dataFolder;}
    public static String getDataFolder() {return DataFolder;}
    private static HashMap<String, String> messages = new HashMap<>();
    private static Random random = new Random();
    public static Random getRandom() {return random;}

    public static void SendMessageFromConfig(Player p, String messageID)
    {
        p.sendMessage(getMessage(messageID));
    }

    public static void LoadMessages()
    {
        File file = new File(DataFolder + "Messages.yml");
        if (!file.exists()) {try {file.createNewFile();} catch (IOException e) {throw new RuntimeException(e);}}
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section = config.getConfigurationSection("Messages");
        for (String key : section.getKeys(false)) {messages.put(key, section.getString(key));}
    }

    public static String getMessage(String messageID)
    {
        return messages.getOrDefault(messageID, "§cСообщение не найдено");
    }

    public static String getMessage(String messageID, HashMap<String, String> placeholders)
    {
        if (messages.containsKey(messageID)) {return "§cСообщение не найдено";}

        String message = messages.get(messageID);
        for(String placeholder : placeholders.keySet())
        {
            message = message.replaceAll(placeholder, placeholders.get(placeholder));
        }

        return message;
    }
}