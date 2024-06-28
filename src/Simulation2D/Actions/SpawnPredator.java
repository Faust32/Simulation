package Package.Actions;

import Package.Entities.Creature;
import Package.Entities.EntityName;
import Package.Entities.Predator;
import Package.Coordinates;
import Package.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

import static Package.MapDimension.height;
import static Package.MapDimension.width;


public class SpawnPredator extends Action {
    int numberOfPredators = (height * width) / 16 - 3;
    public void fillWithPredators(EntityMap entityMap) {
        while (numberOfPredators > 0) {
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, height);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, width);
            Coordinates coordinates = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (entityMap.isCellEmpty(coordinates)) {
                Creature wolf = new Predator(coordinates, new EntityName("WOLF"));
                entityMap.putInMap(coordinates, wolf);
                numberOfPredators--;
            }
        }
    }
}
