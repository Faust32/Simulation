package Simulation2D.Entities;

import Simulation2D.Coordinates;
import Simulation2D.SearchAlgorithm;
import Simulation2D.EntityMap;

import java.util.Deque;

public class Predator extends Creature {
    private final int attackDamage = 5;

    public Predator(Coordinates coordinates, EntityName entityName) {
        super(coordinates, entityName);
        super.healthPoints = 50;
        super.movementSpeed = 2;
    }

    public int getAttackDamage() {
        return this.attackDamage;
    }

    SearchAlgorithm searchAlgorithm = new SearchAlgorithm();

    private void attackHerbivore(EntityMap entityMap, Coordinates coordinateForAttack) {
        Entity creature = entityMap.getEntityFromMap(coordinateForAttack);
        if (creature instanceof Herbivore) {
            ((Herbivore)creature).changeHealthPoints(-attackDamage);
            if (((Herbivore) creature).isDead()) {
                entityMap.removeFromMap(coordinateForAttack);
                this.changeHealthPoints(3);
            }
        }
    }

    @Override
    public void makeMove(Coordinates currentPosition, EntityMap entityMap) {
        final Deque<Coordinates> pathForPray = searchAlgorithm.getPathForPray(currentPosition, entityMap);
        final Deque<Coordinates> lastSteps = searchAlgorithm.getLastSteps();
        if (pathForPray.isEmpty()) {
            return;
        }
        Entity predator = entityMap.getEntityFromMap(currentPosition);
        Coordinates positionToMove = pathForPray.poll();

        // проверка циклического движения, чтобы животные не ходили туда-сюда
        if (lastSteps.contains(positionToMove)) {
            pathForPray.clear();
            searchAlgorithm.findPray(currentPosition, entityMap);
            if (pathForPray.isEmpty()) {
                return;
            }
            positionToMove = pathForPray.poll();
        }

        // обновляется память последних шагов, чтобы избежать повторов
        int memorySize = 5;
        if (lastSteps.size() >= memorySize) {
            lastSteps.removeFirst();
        }
        lastSteps.addLast(currentPosition);
        // совершается движение/атака
        Entity entityAtNewPosition = entityMap.getEntityFromMap(positionToMove);
        if (entityAtNewPosition instanceof Herbivore) {
            attackHerbivore(entityMap, positionToMove);
            return;
        } else if (entityAtNewPosition instanceof Predator) {
            return;
        }
        predator.updateCoordinates(positionToMove);
        entityMap.putInMap(positionToMove, predator);
        entityMap.removeFromMap(currentPosition);
    }

}
