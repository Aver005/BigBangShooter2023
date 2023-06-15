package kiviuly.bigbangshooter.game.match;

import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum MatchItem
{
    OPERATOR (Material.NETHER_STAR, "§f§lВаши агенты", 0),
    SHOP (Material.CHEST, "§6§lПокупка оперативников", 1);

    public Material material;
    public String name;
    private ItemStack item;
    private int slot;

    MatchItem(Material material, String name, int slot)
    {
        this.material = material;
        this.name = name;
        this.slot = slot;
        this.item = new ItemBuilder(material).setDisplayName(name).build();
    }

    public void Give(User u) {u.getPlayer().getInventory().setItem(this.slot, this.item);}

    public Material getMaterial() {return material;}
    public String getName() {return name;}
    public ItemStack getItem() {return item;}
}
