package Simulation2D.Actions;

import Simulation2D.Coordinates;
import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Stationary.Tree;
import Simulation2D.EntityMap;
import Simulation2D.Simulation;



public class SpawnTree extends SpawnAction {

    public SpawnTree(EntityMap entityMap) {
        this.numberOfEntities = (entityMap.height * entityMap.width) / 16;
    }

    @Override
    public Entity spawnEntity(Coordinates coordinates) {
        return new Tree();
    }
}