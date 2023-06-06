package kiviuly.bigbangshooter.game.user;

import org.bukkit.Material;

import java.util.ArrayList;

public class Operator
{
    private final String ID;
    private final String name;

    private final Material icon;
    private final ArrayList<Kit> kits;

    private final int buyPrice = 10;
    private final int upgradePrice = 10;
    private final int sellPrice = 10;

    private final int maxUpgradeLevel = 4;

    public Operator(String ID)
    {
        this.ID = ID;
        this.name = ID;

        this.kits = new ArrayList<>();
        this.icon = Material.GOLDEN_SWORD;
    }

    public String getID() {return ID;}
    public String getName() {return name;}
    public int getSellPrice() {return sellPrice;}
    public int getBuyPrice() {return buyPrice;}
    public int getUpgradePrice() {return upgradePrice;}
    public Material getIcon() {return icon;}
    public Kit getKit(int level) {return kits.get(level);}
}