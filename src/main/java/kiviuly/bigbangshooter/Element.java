package kiviuly.bigbangshooter;

import org.bukkit.configuration.file.YamlConfiguration;

public abstract class Element
{
    public abstract String getID();
    public abstract void Save(YamlConfiguration config);
    public abstract void Load(YamlConfiguration config);

}