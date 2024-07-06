package Simulation2D.Actions;

import Simulation2D.Entities.Entity;
import Simulation2D.Coordinates;
import Simulation2D.Entities.Herbivore;
import Simulation2D.EntityMap;


public class SpawnHerbivore extends SpawnAction {

    public SpawnHerbivore(EntityMap entityMap) {
        this.numberOfEntities = (entityMap.height * entityMap.width) / 16 - 1;
    }

    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Herbivore(coordinates);
    }
}
