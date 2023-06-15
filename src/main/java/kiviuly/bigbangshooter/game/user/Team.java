package kiviuly.bigbangshooter.game.user;

import org.bukkit.Material;

import java.util.HashMap;

public enum Team
{
    NONE("§f§lНе выбрана", Material.BARRIER),
    DEFENSE("§9§lЗащита", Material.LIGHT_BLUE_BANNER),
    ATTACK("§6§lАтака", Material.ORANGE_BANNER);

    private final String name;
    private final Material icon;

    Team(String name, Material icon)
    {
        this.name = name;
        this.icon = icon;
    }

    public static Team FindByDisplayName(String name)
    {
        for(Team team : values())
        {
            if (team.getName().equals(name))
                return team;
        }

        return null;
    }

    public String getName() {return name;}
    public Material getIcon() {return icon;}
}
