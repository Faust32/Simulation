package Simulation2D.Entities;

import Simulation2D.Coordinates;
import Simulation2D.SearchAlgorithm;
import Simulation2D.EntityMap;
import Simulation2D.Entities.Stationary.Grass;

import java.util.Deque;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, EntityName entityName) {
        super(coordinates, entityName);
        super.healthPoints = 35;
        super.movementSpeed = 1;
    }

    SearchAlgorithm searchAlgorithm = new SearchAlgorithm();

    @Override
    public void makeMove(Coordinates currentPosition, EntityMap entityMap) {
        Deque<Coordinates> pathForGrass = searchAlgorithm.getPathForGrass(currentPosition, entityMap);
        Deque<Coordinates> lastSteps = searchAlgorithm.getLastSteps();
        if (isDead()) {
            entityMap.removeFromMap(currentPosition);
        }
        if (pathForGrass.isEmpty()) {
            return;
        }
        Entity herbivore = entityMap.getEntityFromMap(currentPosition);
        if (herbivore == null) {
            return;
        }
        Coordinates positionToMove = pathForGrass.poll();

        // проверка циклического движения, чтобы животные не ходили туда-сюда
        if (lastSteps.contains(positionToMove)) {
            pathForGrass.clear();
            searchAlgorithm.findGrass(currentPosition, entityMap);
            if (pathForGrass.isEmpty()) {
                return;
            }
            positionToMove = pathForGrass.poll();
        }
        // обновляется память последних шагов, чтобы избежать повторов
        int memorySize = 5;
        if (lastSteps.size() >= memorySize) {
            lastSteps.removeFirst();
        }
        lastSteps.addLast(currentPosition);
        // совершается движение
        Entity entityAtNewPosition = entityMap.getEntityFromMap(positionToMove);
        if (entityAtNewPosition instanceof Grass) {
            entityMap.removeFromMap(positionToMove);
            this.changeHealthPoints(7);
        } else if (entityAtNewPosition instanceof Predator) {
            return;
        }
        herbivore.updateCoordinates(positionToMove);
        entityMap.putInMap(positionToMove, herbivore);
        entityMap.removeFromMap(currentPosition);
    }

}
