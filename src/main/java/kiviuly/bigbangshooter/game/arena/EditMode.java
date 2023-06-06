package kiviuly.bigbangshooter.game.arena;

import kiviuly.bigbangshooter.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EditMode
{
    public static void GiveEditItems(Arena arena, Player p)
    {
        for(EditItem item : EditItem.values())
        {
            ItemStack is = new ItemStack(item.getMaterial());
            ItemMeta im = is.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add("§f- Арена: §b§l" + arena.getID());
            im.setDisplayName(item.getName());
            im.setLore(lore);
            is.setItemMeta(im);
            p.getInventory().addItem(is);
        }

        DataManager.SendMessageFromConfig(p, "EditItemsGiven");
    }
}
