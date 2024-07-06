package Simulation2D;

import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Herbivore;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import Simulation2D.Entities.Predator;
import Simulation2D.Entities.Stationary.Grass;

public class SearchAlgorithm {
    private final Deque<Coordinates> reachablePoints = new LinkedList<>();
    private final HashMap<Coordinates, Coordinates> previousNodes = new HashMap<>();
    private final ArrayList<Coordinates> explored = new ArrayList<>();
    private final Deque<Coordinates> pathForObject = new LinkedList<>();
    private final Deque<Coordinates> lastSteps = new LinkedList<>();

    private boolean isCoordinateInMap(Coordinates coordinate, EntityMap entityMap) {
        return coordinate.getX() <= entityMap.width && coordinate.getY() <= entityMap.height && coordinate.getX() > 0 && coordinate.getY() > 0;
    }

    public void updateLastSteps(Deque<Coordinates> lastSteps, Coordinates currentPosition, int memorySize) {
        if (lastSteps.size() >= memorySize) {
            lastSteps.removeFirst();
        }
        lastSteps.addLast(currentPosition);
    }

    public Coordinates checkForCycleMovements(Deque<Coordinates> lastSteps, Coordinates positionToMove, Coordinates currentPosition, EntityMap entityMap, Deque<Coordinates> path) {
        if (lastSteps.contains(positionToMove)) {
            path.clear();
            findObject(currentPosition, entityMap);
            if (path.isEmpty()) {
                return null;
            }
            positionToMove = path.poll();
        }
        return positionToMove;
    }

    private void addNewReachablePoint(Coordinates newCoordinate, Coordinates currentCoordinate, EntityMap entityMap) {
        if (isCoordinateInMap(newCoordinate, entityMap) && !explored.contains(newCoordinate)
                && !reachablePoints.contains(newCoordinate)
                && (entityMap.get(newCoordinate) instanceof Herbivore
                || entityMap.get(newCoordinate) instanceof Grass
                || entityMap.get(newCoordinate) == null)
                && !lastSteps.contains(newCoordinate)) {  // проверка памяти
            reachablePoints.addLast(newCoordinate);
            previousNodes.put(newCoordinate, currentCoordinate);
        }
    }

    private void getNewReachablePoints(Coordinates currentCoordinates, EntityMap entityMap) {
        Coordinates right = new Coordinates(currentCoordinates.getX() + 1, currentCoordinates.getY());
        Coordinates left = new Coordinates(currentCoordinates.getX() - 1, currentCoordinates.getY());
        Coordinates up = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() + 1);
        Coordinates down = new Coordinates(currentCoordinates.getX(), currentCoordinates.getY() - 1);

        addNewReachablePoint(right, currentCoordinates, entityMap);
        addNewReachablePoint(left, currentCoordinates, entityMap);
        addNewReachablePoint(up, currentCoordinates, entityMap);
        addNewReachablePoint(down, currentCoordinates, entityMap);
    }

    private void buildPath(Coordinates endCoordinates, Coordinates startCoordinates) {
        pathForObject.clear();
        Coordinates currentCoordinates = endCoordinates;
        while (currentCoordinates != null && !currentCoordinates.equals(startCoordinates)) {
            pathForObject.addFirst(currentCoordinates);
            currentCoordinates = previousNodes.get(currentCoordinates);
        }
        pathForObject.addFirst(startCoordinates);
    }

    private void findObject(Coordinates startCoordinates, EntityMap entityMap) {
        reachablePoints.clear();
        previousNodes.clear();
        explored.clear();
        reachablePoints.add(startCoordinates);
        previousNodes.put(startCoordinates, null);
        boolean found = false;
        while (!reachablePoints.isEmpty() && !found) {
            Coordinates node = reachablePoints.poll();
            explored.add(node);

            Entity creature = entityMap.get(startCoordinates);
            Entity targetEntity = entityMap.get(node);

            if (targetEntity instanceof Grass && creature instanceof Herbivore) {
                checkAndBuildPath(node, startCoordinates);
                found = true;
            }

            else if (targetEntity instanceof Herbivore && creature instanceof Predator) {
                checkAndBuildPath(node, startCoordinates);
                found = true;
            }

            getNewReachablePoints(node, entityMap);
        }
    }

    private void checkAndBuildPath(Coordinates node, Coordinates startCoordinates) {
        buildPath(node, startCoordinates);
        pathForObject.pop();
    }

    public Deque<Coordinates> getPathForObject(Coordinates startCoordinates, EntityMap entityMap){
        findObject(startCoordinates, entityMap);
        return pathForObject;
    }

    public Deque<Coordinates> getLastSteps(){
        return lastSteps;
    }

}