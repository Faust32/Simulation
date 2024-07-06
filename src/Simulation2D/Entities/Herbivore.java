package Simulation2D.Entities;

import Simulation2D.Coordinates;
import Simulation2D.EntityMap;
import Simulation2D.Entities.Stationary.Grass;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates) {
        super(coordinates);
        super.entityName = "HERB";
        super.healthPoints = 35;
        super.movementSpeed = 1;
    }

    @Override
    public void interact(EntityMap entityMap, Coordinates positionToMove, Entity entityAtNewPosition) {
        if (entityAtNewPosition instanceof Predator) {
            return;
        }
        entityMap.remove(this.coordinates);
        if (entityAtNewPosition instanceof Grass) {
            entityMap.remove(positionToMove);
            this.changeHealthPoints(6);
        }
        this.updateCoordinates(positionToMove);
        entityMap.put(positionToMove, this);
    }

}
