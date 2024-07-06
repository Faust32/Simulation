package Simulation2D.Actions;

import Simulation2D.Coordinates;
import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Stationary.Grass;
import Simulation2D.EntityMap;

public class SpawnGrass extends SpawnAction {

    public SpawnGrass(EntityMap entityMap) {
        this.numberOfEntities = (entityMap.height * entityMap.width) / 16;
    }

    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Grass();
    }

}