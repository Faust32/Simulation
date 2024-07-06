package Simulation2D.Actions;

import Simulation2D.Coordinates;
import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Stationary.Rock;
import Simulation2D.EntityMap;
import Simulation2D.Simulation;


public class SpawnRock extends SpawnAction {

    public SpawnRock(EntityMap entityMap) {
        this.numberOfEntities = (entityMap.height * entityMap.width) / 16;
    }

    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Rock();
    }
}