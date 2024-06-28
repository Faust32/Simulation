package Package.Actions;

import Package.Coordinates;
import Package.Entities.EntityName;
import Package.Entities.Stationary.Rock;
import Package.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

import static Package.MapDimension.height;
import static Package.MapDimension.width;


public class SpawnRock extends Action {
    int numberOfRocks = (height * width) / 16;
    public void fillWithRocks(EntityMap entityMap) {
        while (numberOfRocks > 0) {
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, 11);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, 11);
            Coordinates coordinates = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (entityMap.isCellEmpty(coordinates)) {
                entityMap.putInMap(coordinates, new Rock(coordinates, new EntityName("ROCK")));
                numberOfRocks--;
            }
        }
    }

}