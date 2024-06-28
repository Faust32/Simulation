package Package;
import Package.Entities.Entity;

import java.util.HashMap;

public class EntityMap {
    private final HashMap<Coordinates, Entity> map = new HashMap<>();

    public Entity getFromMap(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !map.containsKey(coordinates);
    }

    public void putInMap(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
    }

    public void removeFromMap(Coordinates coordinates) {
        map.remove(coordinates);
    }

    public Iterable<? extends java.util.Map.Entry<Coordinates, Entity>> entrySet() {
        return map.entrySet();
    }

    public boolean containsValue(Entity creature) {
        return map.containsValue(creature);
    }
}
