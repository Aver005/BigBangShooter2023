package kiviuly.bigbangshooter.game.arena;

public enum ArenaState
{
    ENABLED("§aВключена", ""),
    LOBBY_NOT_SET("§eЛобби не установлено", ""),
    TEAM_SPAWNS_EMPTY("§eКоманды не настроены", ""),
    DISABLED("§cВыключена", "");

    private final String name;
    private final String description;

    ArenaState(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
}
