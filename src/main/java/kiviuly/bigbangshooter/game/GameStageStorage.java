package kiviuly.bigbangshooter.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GameStageStorage<T extends GameStage>
{
    private HashMap<UUID, T> elements = new HashMap<>();

    public void add(T element) {elements.put(element.getID(), element);}
    public T get(UUID ID) {return elements.getOrDefault(ID, null);}
    public void remove(T element) {elements.remove(element.getID());}
    public ArrayList<T> getAvailable() {return new ArrayList<>(elements.values());}
}