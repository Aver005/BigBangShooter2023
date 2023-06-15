package kiviuly.bigbangshooter.game.lobby;

import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum LobbyItem
{
    TEAM (Material.WHITE_BANNER, "§f§lВыбор команды", 0),
    OPERATOR (Material.NETHER_STAR, "§f§lНачальный оперативник", 1),
    EXIT (Material.BARRIER, "§c§lВыйти из лобби", 8);

    private Material material;
    private String name;
    private ItemStack item;
    private int slot;

    LobbyItem(Material material, String name, int slot)
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