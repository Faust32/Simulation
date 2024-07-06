package Simulation2D.Entities;

import Simulation2D.Coordinates;
import Simulation2D.SearchAlgorithm;
import Simulation2D.EntityMap;

public class Predator extends Creature {
    private final int attackDamage = 5;
    private final SearchAlgorithm searchAlgorithm = new SearchAlgorithm();

    public Predator(Coordinates coordinates) {
        super(coordinates);
        super.entityName = "PRED";
        super.healthPoints = 50;
        super.movementSpeed = 2;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    protected void attack(EntityMap entityMap, Coordinates coordinateForAttack) {
        Entity creature = entityMap.get(coordinateForAttack);
        if (creature instanceof Herbivore) {
            ((Herbivore)creature).changeHealthPoints(-attackDamage);
            if (((Herbivore) creature).isDead()) {
                entityMap.remove(coordinateForAttack);
                this.changeHealthPoints(3);
            }
        }
    }

    @Override
    public void interact(EntityMap entityMap, Coordinates positionToMove, Entity entityAtNewPosition) {
        if (entityAtNewPosition instanceof Herbivore) {
            this.attack(entityMap, positionToMove);
            return;
        } else if (entityAtNewPosition instanceof Predator) {
            return;
        }
        entityMap.remove(this.coordinates);
        this.updateCoordinates(positionToMove);
        entityMap.put(positionToMove, this);

    }



}
