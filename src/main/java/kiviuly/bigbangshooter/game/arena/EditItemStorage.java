package kiviuly.bigbangshooter.game.arena;

import kiviuly.bigbangshooter.game.user.Team;
import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.HashMap;

public class EditItemStorage
{
    private static HashMap<Arena, ArrayList<ItemStack>> itemsByArena = new HashMap<>();
    private static HashMap<EditItem, ArrayList<ItemStack>> itemByEdit = new HashMap<>();

    private static boolean IsValidUser(User user)
    {
        if (user == null) {return false;}
        if (!user.isEditing()) {return false;}
        if (!itemsByArena.containsKey(user.getEditArena())) {GenerateItemsForArena(user.getEditArena());}
        return true;
    }

    private static void AddItemForEditItem(EditItem editItem, ItemStack item)
    {
        ArrayList<ItemStack> items = itemByEdit.getOrDefault(editItem, new ArrayList<>());
        items.add(item);
        itemByEdit.put(editItem, items);
    }

    public static EditItem GetEditByItem(ItemStack is)
    {
        for(EditItem editItem : EditItem.values())
        {
            if (!itemByEdit.containsKey(editItem)) {continue;}
            if (itemByEdit.get(editItem).contains(is)) {return editItem;}
        }

        return null;
    }

    public static void GiveItemsToUser(User user)
    {
        if (!IsValidUser(user)) {return;}

        ArrayList<ItemStack> items = itemsByArena.get(user.getEditArena());
        PlayerInventory inv = user.getPlayer().getInventory();

        for (ItemStack item : items)
        {
            if (inv.contains(item)) {continue;}
            inv.addItem(item);
        }
    }

    public static void RemoveItemsFromUser(User user)
    {
        if (!IsValidUser(user)) {return;}

        ArrayList<ItemStack> items = itemsByArena.get(user.getEditArena());
        PlayerInventory inv = user.getPlayer().getInventory();

        for (ItemStack item : items)
        {
            if (!inv.contains(item)) {continue;}
            inv.remove(item);
        }
    }

    public static void GenerateItemsForArena(Arena arena)
    {
        ArrayList<ItemStack> items = new ArrayList<>();

        ItemStack is = new ItemBuilder(EditItem.LOBBY_SPAWN_SELECTOR.getItem())
            .addLore("§8§l================")
            .addLore("")
            .addLore("§f§lАрена: §e§l" + arena.getID())
            .addLore("")
            .addLore("§8§l================")
        .build();
        items.add(is);
        AddItemForEditItem(EditItem.LOBBY_SPAWN_SELECTOR, is);

        for(Team team : Team.values())
        {
            is = new ItemBuilder(team.getIcon())
                .addLore("§8§l================")
                .addLore("")
                .addLore("§f§lАрена: §e§l" + arena.getID())
                .addLore("§f§lКоманда: " + team.getName())
                .addLore("")
                .addLore("§8§l================")
            .build();
            items.add(is);
            AddItemForEditItem(EditItem.TEAM_SPAWN_SELECTOR, is);
        }

        itemsByArena.put(arena, items);
    }

    public static boolean IsEditItem(ItemStack is, Arena arena)
    {
        if (!itemsByArena.containsKey(arena)) {return false;}
        return itemsByArena.get(arena).contains(is);
    }
}