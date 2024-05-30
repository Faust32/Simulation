package Package;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Map {
    public HashMap<Coordinates, Entity> map = new HashMap<>();

    public Entity getFromMap(Coordinates coordinates) {
        return map.get(coordinates);
    }

//    public void putObject(Coordinates coordinates, Entity entity){
//        entity.coordinates = coordinates;
//        map.put(coordinates, entity);
//    }
//    public void putObject(Coordinates coordinates, Herbivore creature) {
//        creature.coordinates = coordinates;
//        map.put(coordinates, creature);
//    }

    private boolean isCellEmpty(Coordinates coordinates) {
        return !map.containsKey(coordinates);
    }

    private void fillWithGrass(int numberOfGrass){
        while (numberOfGrass>0){
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, 11);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, 11);
            Coordinates coord = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (isCellEmpty(coord)) {
                map.put(coord, new Entity.Grass(coord, new EntityName("GRSS")));
                numberOfGrass--;
            }
        }
    }

    private void fillWithRocks(int numberOfRocks){
        while (numberOfRocks>0){
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, 11);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, 11);
            Coordinates coord = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (isCellEmpty(coord)) {
                map.put(coord, new Entity.Rock(coord, new EntityName("ROCK")));
                numberOfRocks--;
            }
        }
    }

    private void fillWithTrees(int numberOfTrees){
        while (numberOfTrees>0){
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, 11);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, 11);
            Coordinates coord = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (isCellEmpty(coord)) {
                map.put(coord, new Entity.Tree(coord, new EntityName("TREE")));
                numberOfTrees--;
            }
        }
    }

    private void fillWithCows(int numberOfCows){
        while (numberOfCows>0){
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, 11);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, 11);
            Coordinates coord = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (isCellEmpty(coord)) {
                map.put(coord, new Herbivore(coord, new EntityName("COW_"), new HealthPoints(30), new MovementSpeed(1)));
                numberOfCows--;
            }
        }
    }

    private void fillWithPigs(int numberOfPigs){
        while (numberOfPigs>0){
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, 11);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, 11);
            Coordinates coord = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (isCellEmpty(coord)) {
                map.put(coord, new Herbivore(coord, new EntityName("PIG_"), new HealthPoints(40), new MovementSpeed(1)));
                numberOfPigs--;
            }
        }
    }

    private void fillWithWolfs(int numberOfWolfs){
        while (numberOfWolfs>0){
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, 11);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, 11);
            Coordinates coord = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (isCellEmpty(coord)) {
                map.put(coord, new Predator(coord, new EntityName("WOLF"), new HealthPoints(50), new MovementSpeed(2), 5));
                numberOfWolfs--;
            }
        }
    }

    public void fillMapRandomly(){
        int numberOfTrees = ThreadLocalRandom.current().nextInt(2, 5);
        int numberOfRocks = ThreadLocalRandom.current().nextInt(2, 5);
        int numberOfGrass = ThreadLocalRandom.current().nextInt(3, 6);
        int numberOfCows = ThreadLocalRandom.current().nextInt(3, 5);
        int numberOfPigs = ThreadLocalRandom.current().nextInt(3, 5);
        int numberOfWolfs = ThreadLocalRandom.current().nextInt(2, 5);

        fillWithGrass(numberOfGrass);

        fillWithRocks(numberOfRocks);

        fillWithTrees(numberOfTrees);

        fillWithCows(numberOfCows);

        fillWithPigs(numberOfPigs);

        fillWithWolfs(numberOfWolfs);
    }

}
