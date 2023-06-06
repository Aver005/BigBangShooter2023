package kiviuly.bigbangshooter.game.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MatchStorage
{
    private static HashMap<UUID, Match> matches = new HashMap<>();


    public static void add(Match match) {matches.put(match.getID(), match);}
    public static Match get(UUID ID) {return matches.getOrDefault(ID, null);}

    public static ArrayList<Match> getAvailable()
    {
        ArrayList<Match> availableMatches = new ArrayList<>();

        for(UUID ID : matches.keySet())
        {
            Match match = matches.get(ID);
            if (!match.getStatus().equals(MatchStatus.LOBBY)) {continue;}
            availableMatches.add(match);
        }

        return availableMatches;
    }
}