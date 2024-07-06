package Simulation2D.Actions;

import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Predator;
import Simulation2D.Coordinates;
import Simulation2D.EntityMap;


public class SpawnPredator extends SpawnAction {
    public SpawnPredator(EntityMap entityMap){
        super.numberOfEntities = (entityMap.height * entityMap.width) / 30;
    }

    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Predator(coordinates);
    }
}
