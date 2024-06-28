package Package.Entities;

import Package.Coordinates;
import Package.SearchAlgorithm;
import Package.EntityMap;
import Package.Entities.Stationary.Grass;

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
        Entity herbivore = entityMap.getFromMap(currentPosition);
        if (herbivore == null) {
            return;
        }
        Coordinates positionToMove = pathForGrass.poll();

        // проверка циклического движения
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
        Entity entityAtNewPosition = entityMap.getFromMap(positionToMove);
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
