package Package.Actions;

import Package.Entities.Creature;
import Package.Entities.EntityName;
import Package.Entities.Herbivore;
import Package.Coordinates;
import Package.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

import static Package.MapDimension.height;
import static Package.MapDimension.width;

public class SpawnHerbivore extends Action {
    int numberOfHerbivore = (height * width) / 16 - 1;
    private void fillWithCows(EntityMap entityMap) {
        while (numberOfHerbivore > 0) {
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, height);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, width);
            Coordinates coordinates = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (entityMap.isCellEmpty(coordinates)) {
                Creature cow = new Herbivore(coordinates, new EntityName("COW_"));
                entityMap.putInMap(coordinates, cow);
                numberOfHerbivore--;
            }
        }
    }

    private void fillWithPigs(EntityMap entityMap) {
        while (numberOfHerbivore > 0) {
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, height);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, width);
            Coordinates coordinates = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (entityMap.isCellEmpty(coordinates)) {
                Creature pig = new Herbivore(coordinates, new EntityName("PIG_"));
                entityMap.putInMap(coordinates, pig);
                numberOfHerbivore--;
            }
        }
    }
    public void fillWithHerbivores(EntityMap entityMap) {
        fillWithCows(entityMap);
        fillWithPigs(entityMap);
    }
}
