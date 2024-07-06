package Simulation2D;
import Simulation2D.Entities.Entity;

import java.util.HashMap;

public class EntityMap {
    public int height;
    public int width;

    private final HashMap<Coordinates, Entity> map = new HashMap<>();

    public EntityMap(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Entity get(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !map.containsKey(coordinates);
    }

    public void put(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
    }

    public void remove(Coordinates coordinates) {
        map.remove(coordinates);
    }

    public Iterable<? extends java.util.Map.Entry<Coordinates, Entity>> getEntries() {
        return map.entrySet();
    }

    public boolean containsEntity(Entity entity) {
        return map.containsValue(entity);
    }

}
