package Simulation2D.Actions;

import Simulation2D.Coordinates;
import Simulation2D.Entities.EntityName;
import Simulation2D.Entities.Stationary.Rock;
import Simulation2D.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

import static Simulation2D.MapDimension.height;
import static Simulation2D.MapDimension.width;


public class SpawnRock extends Action {
    private int numberOfRocks = (height * width) / 16;
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