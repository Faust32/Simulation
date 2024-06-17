package Package;
import java.util.HashMap;

public class Map {
    private HashMap<Coordinates, Entity> map = new HashMap<>();

    public Entity getFromMap(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public HashMap<Coordinates, Entity> getMap(){
        return map;
    }

    public boolean containsKey(Coordinates coordinates) {
        return map.containsKey(coordinates);
    }

    public void put(Coordinates coordinates, Entity entity) {
        map.put(coordinates, entity);
    }

    public void remove(Coordinates coordinates) {
        map.remove(coordinates);
    }

    public Iterable<? extends java.util.Map.Entry<Coordinates, Entity>> entrySet() {
        return map.entrySet();
    }


//    public void putObject(Coordinates coordinates, Entity entity){
//        entity.coordinates = coordinates;
//        map.put(coordinates, entity);
//    }
//    public void putObject(Coordinates coordinates, Herbivore creature) {
//        creature.coordinates = coordinates;
//        map.put(coordinates, creature);
//    }



}
