package Simulation2D.Actions;

import Simulation2D.Entities.Creature;
import Simulation2D.Entities.EntityName;
import Simulation2D.Entities.Predator;
import Simulation2D.Coordinates;
import Simulation2D.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

import static Simulation2D.MapDimension.height;
import static Simulation2D.MapDimension.width;


public class SpawnPredator extends Action {
    private int numberOfPredators = (height * width) / 30;
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
