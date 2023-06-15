package kiviuly.bigbangshooter.game.arena;

import kiviuly.bigbangshooter.DataManager;
import kiviuly.bigbangshooter.game.user.Team;
import kiviuly.bigbangshooter.game.user.User;
import kiviuly.bigbangshooter.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public enum EditItem
{
    TEAM_SPAWN_SELECTOR("§f§lОтметчик спавнов игроков", Material.WHITE_BANNER)
    {
        @Override
        public void onPlace(User user, BlockPlaceEvent e)
        {
            e.setCancelled(true);

            ItemStack is = e.getItemInHand();
            if (!is.hasItemMeta())
            {
                DataManager.SendMessageFromConfig(user.getPlayer(), "SpawnSelectorItemMetaError");
                return;
            }

            ItemMeta meta = is.getItemMeta();
            if (!meta.hasLore())
            {
                DataManager.SendMessageFromConfig(user.getPlayer(), "SpawnSelectorLoreError");
                return;
            }

            List<String> lore = meta.getLore();
            if (lore.size() < 4)
            {
                DataManager.SendMessageFromConfig(user.getPlayer(), "SpawnSelectorLoreSizeError");
                return;
            }

            String name = lore.get(3).replace("§f§lКоманда: ", "");
            Team team = Team.FindByDisplayName(name);
            if (team == null)
            {
                DataManager.SendMessageFromConfig(user.getPlayer(), "SpawnSelectorTeamNotFound");
                return;
            }

            user.getEditArena().addSpawn(team, e.getBlock().getLocation());
            DataManager.SendMessageFromConfig(user.getPlayer(), "ArenaTeamSpawnAdd");
        }
    },

    LOBBY_SPAWN_SELECTOR("§f§lОтметчик точки лобби", Material.BEACON)
    {
        @Override
        public void onPlace(User user, BlockPlaceEvent e)
        {
            e.setCancelled(true);
            user.getEditArena().setLobby(e.getBlock().getLocation());
            DataManager.SendMessageFromConfig(user.getPlayer(), "ArenaLobbySet");
        }
    };

    private String name;
    private Material material;
    private ItemStack item;

    EditItem(String name, Material material)
    {
        this.name = name;
        this.material = material;
        this.item = new ItemBuilder(material).setDisplayName(name).build();
    }

    public void onUse(User user, PlayerInteractEvent e) {e.setCancelled(true);}
    public void onPlace(User user, BlockPlaceEvent e) {e.setCancelled(true);}

    public String getName() {return name;}
    public Material getMaterial() {return material;}
    public ItemStack getItem() {return item;}
}