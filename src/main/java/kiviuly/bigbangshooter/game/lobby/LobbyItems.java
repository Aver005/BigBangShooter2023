package kiviuly.bigbangshooter.game.lobby;

import kiviuly.bigbangshooter.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LobbyItems
{
    private static ItemStack ChooseTeamItem;
    private static ItemStack DefenseTeamItem;
    private static ItemStack AttackTeamItem;
    private static ItemStack ExitItem;

    public static ItemStack GetChooseTeamItem() {return ChooseTeamItem;}
    public static ItemStack GetExitItem() {return ExitItem;}

    public static void Load()
    {
        ChooseTeamItem = new ItemBuilder(Material.WHITE_BANNER)
                .setDisplayName("§fВыбор команды")
                .addLore("")
                .addLore("§8╔═══════════")
                .addLore("§8║")
                .addLore("§8║ §fНажмите §e§lПКМ §fдля открытия")
                .addLore("§8║ §fменю §eвыбора команды")
                .addLore("§8║")
                .addLore("§8║ §fСейчас выбрана:")
                .addLore("§8║ §f§lЛЮБАЯ КОМАНДА")
                .addLore("§8║")
                .addLore("§8╚═══════════")
                .addLore("")
        .build();

        DefenseTeamItem = new ItemBuilder(Material.BLUE_BANNER)
                .setDisplayName("§fВыбор команды §9§l[Защита]")
                .addLore("")
                .addLore("§8╔═══════════")
                .addLore("§8║")
                .addLore("§8║ §fНажмите §e§lПКМ §fдля открытия")
                .addLore("§8║ §fменю §eвыбора команды")
                .addLore("§8║")
                .addLore("§8║ §fСейчас выбрана:")
                .addLore("§8║ §9§lЗАЩИТА")
                .addLore("§8║")
                .addLore("§8╚═══════════")
                .addLore("")
        .build();

        AttackTeamItem = new ItemBuilder(Material.ORANGE_BANNER)
                .setDisplayName("§fВыбор команды §6§l[Атака]")
                .addLore("")
                .addLore("§8╔═══════════")
                .addLore("§8║")
                .addLore("§8║ §fНажмите §e§lПКМ §fдля открытия")
                .addLore("§8║ §fменю §eвыбора команды")
                .addLore("§8║")
                .addLore("§8║ §fСейчас выбрана:")
                .addLore("§8║ §6§lАТАКА")
                .addLore("§8║")
                .addLore("§8╚═══════════")
                .addLore("")
        .build();

        ExitItem = new ItemBuilder(Material.BARRIER)
                .setDisplayName("§cВыйти")
                .addLore("")
                .addLore("§8╔═══════════")
                .addLore("§8║")
                .addLore("§8║ §fНажмите §e§lПКМ §fдля")
                .addLore("§8║ §cвыхода из матча")
                .addLore("§8║")
                .addLore("§8╚═══════════")
                .addLore("")
        .build();
    }

    public static boolean IsChooseTeamItem(ItemStack is)
    {
        return is.equals(ChooseTeamItem) || is.equals(DefenseTeamItem) || is.equals(AttackTeamItem);
    }

    public static boolean IsExitItem(ItemStack is) {return is.equals(ExitItem);}
}