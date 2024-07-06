package Simulation2D.Entities;
import Simulation2D.Coordinates;
import Simulation2D.EntityMap;
import Simulation2D.SearchAlgorithm;

import java.util.Deque;


public abstract class Creature extends Entity {
    private final SearchAlgorithm searchAlgorithm = new SearchAlgorithm();
    protected Coordinates coordinates;
    protected int healthPoints;
    protected int movementSpeed;
    public Creature(Coordinates coordinates) {
        this.coordinates = coordinates;
        super.entityName = " ";
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCreatureName() {
        return entityName;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public abstract void interact(EntityMap entityMap, Coordinates positionToMove, Entity entityAtNewPosition);

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public boolean isDead() {
        return healthPoints <= 0;
    }

    public void changeHealthPoints(int value) {
        this.healthPoints += value;
    }

    public void updateCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void makeMove(Coordinates currentPosition, EntityMap entityMap){
        Deque<Coordinates> path = searchAlgorithm.getPathForObject(currentPosition, entityMap);
        Deque<Coordinates> lastSteps = searchAlgorithm.getLastSteps();
        Entity creature = entityMap.get(currentPosition);
        if (isDead()) {
            entityMap.remove(currentPosition);
        }
        if (path.isEmpty()) {
            return;
        }
        if (creature == null) {
            return;
        }
        Coordinates positionToMove = path.poll();
        positionToMove = searchAlgorithm.checkForCycleMovements(lastSteps, positionToMove, currentPosition, entityMap, path);
        if (positionToMove == null) {
            return;
        }
        searchAlgorithm.updateLastSteps(lastSteps, currentPosition, 5);
        Entity entityAtNewPosition = entityMap.get(positionToMove);
        if (creature instanceof Creature) {
            ((Creature) creature).interact(entityMap, positionToMove, entityAtNewPosition);
        }
    }
}

