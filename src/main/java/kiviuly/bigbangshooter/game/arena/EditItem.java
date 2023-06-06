package kiviuly.bigbangshooter.game.arena;

import org.bukkit.Material;

public enum EditItem
{
    DEFENSE_SPAWN (Material.BLUE_BANNER, "§9Отметчик спавнов защиты"),
    ATTACK_SPAWN (Material.ORANGE_BANNER, "§6Отметчик спавнов аттаки"),
    LOBBY_POINT (Material.WHITE_BANNER, "§fОтметчик точки лобби");

    private Material material;
    private String name;

    EditItem(Material material, String name)
    {
        this.material = material;
        this.name = name;
    }

    public Material getMaterial() {return material;}
    public String getName() {return name;}
}