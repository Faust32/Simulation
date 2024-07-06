package Simulation2D.Actions;

import Simulation2D.Coordinates;
import Simulation2D.Entities.Creature;
import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Predator;
import Simulation2D.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

public abstract class SpawnAction extends Action {
    protected int numberOfEntities;

    public Coordinates randomCoordinates(final EntityMap entityMap) {
        int randomCoordinateX;
        int randomCoordinateY;

        do {
            randomCoordinateX = ThreadLocalRandom.current().nextInt(1, entityMap.height+1);
            randomCoordinateY = ThreadLocalRandom.current().nextInt(1, entityMap.width+1);
        } while (!entityMap.isCellEmpty(new Coordinates(randomCoordinateX, randomCoordinateY)));

        return new Coordinates(randomCoordinateX, randomCoordinateY);
    }


    public abstract Entity spawnEntity(final Coordinates coordinates);

    @Override
    public void perform(EntityMap entityMap) {
        while (numberOfEntities > 0) {
            Coordinates coordinates = randomCoordinates(entityMap);
            if (entityMap.isCellEmpty(coordinates)) {
                Entity entity = spawnEntity(coordinates);
                entityMap.put(coordinates, entity);
                numberOfEntities--;
            }
        }
    }
}
