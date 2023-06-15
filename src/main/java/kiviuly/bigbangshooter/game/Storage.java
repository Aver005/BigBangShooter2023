package kiviuly.bigbangshooter.game;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.Element;
import kiviuly.bigbangshooter.Main;
import kiviuly.bigbangshooter.game.arena.Arena;
import kiviuly.bigbangshooter.game.user.User;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public abstract class Storage<T extends Element, J>
{
    private String folderName;
    private String folderPath;
    private final HashMap<J, T> storage = new HashMap<>();

    public Storage(String folderName)
    {
        setFolderName(folderName);
        setFolderPath(Main.getInstance().getDataFolder() + File.separator + getFolderName() + File.separator);
        File folder = new File(getFolderPath());
        if (!folder.exists()) {folder.mkdir();}
        Load();
    }

    public void Save()
    {
        for (J key : getStorage().keySet())
        {
            T el = getStorage().get(key);
            if (el == null) {continue;}

            File file = new File(getFolderPath() + key + ".yml");
            if (!file.exists()) {try {file.createNewFile();} catch (IOException e) {
                DataManager.SendMessageToConsole("CantCreateFile");
            }}

            el.Save(YamlConfiguration.loadConfiguration(file));
        }
    }

    public abstract void Load();
    public abstract void Add(T element);
    public abstract T Get(J key);
    public boolean IsExists(J key) {return getStorage().containsKey(key);}

    protected HashMap<J, T> getStorage() {return storage;}
    protected void setFolderName(String name) {folderName = name;}
    protected void setFolderPath(String path) {folderPath = path;}
    public String getFolderName() {return folderName;}
    public String getFolderPath() {return folderPath;}
}