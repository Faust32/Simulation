package Package;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
    private HashMap<Coordinates, Entity> map = new HashMap<>();

    public Entity getFromMap(Coordinates coordinates) {
        return map.get(coordinates);
    }

    public HashMap<Coordinates, Entity> getMap(){
        return map;
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
