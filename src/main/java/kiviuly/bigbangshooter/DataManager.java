package kiviuly.bigbangshooter;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

public class DataManager
{
    private static String DataFolder;
    private static String MessagePrefix;
    private static HashMap<String, String> messages = new HashMap<>();
    private static Random random = new Random();

    static
    {
        DataFolder = Main.getInstance().getDataFolder() + File.separator;
        LoadMessages();
        MessagePrefix = messages.getOrDefault("Prefix", "§8§l{§e§lBig Bang Shooter§8§l}");
    }


    public static String getDataFolder() {return DataFolder;}
    public static Random getRandom() {return random;}
    public static String getMessage(String mID) {return messages.getOrDefault(mID, "§cСообщение не найдено");}
    public static void SendMessageFromConfig(Player p, String messageID) {p.sendMessage(MessagePrefix + " " +getMessage(messageID));}


    public static void LoadMessages()
    {
        File file = new File(DataFolder + "Messages.yml");
        if (!file.exists()) {return;}
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section = config.getConfigurationSection("Messages");
        for (String key : section.getKeys(false)) {messages.put(key, section.getString(key));}
    }


    public static String getMessage(String messageID, HashMap<String, String> placeholders)
    {
        if (!messages.containsKey(messageID)) {return "§cСообщение не найдено";}

        String message = messages.get(messageID);
        for(String placeholder : placeholders.keySet())
        {
            message = message.replaceAll(placeholder, placeholders.get(placeholder));
        }

        return message;
    }

    public static void SendMessageToConsole(String messageID)
    {

    }
}