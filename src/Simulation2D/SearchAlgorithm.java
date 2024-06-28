package Package;

import Package.Entities.Herbivore;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import Package.Entities.Stationary.Grass;

import static Package.MapDimension.height;
import static Package.MapDimension.width;

public class SearchAlgorithm {

    private final Deque<Coordinates> reachablePoints = new LinkedList<>();
    private final HashMap<Coordinates, Coordinates> previousNodes = new HashMap<>();
    private final ArrayList<Coordinates> explored = new ArrayList<>();
    private final Deque<Coordinates> pathForObject = new LinkedList<>();
    private final Deque<Coordinates> lastSteps = new LinkedList<>();


    private boolean isCoordinateInMap(Coordinates coordinate) {
        return coordinate.getX() <= width && coordinate.getY() <= height && coordinate.getX() > 0 && coordinate.getY() > 0;
    }

    private void addNewReachablePoint(Coordinates newCoordinate, Coordinates currentCoordinate, EntityMap entityMap) {
        if (isCoordinateInMap(newCoordinate) && !explored.contains(newCoordinate)
                && !reachablePoints.contains(newCoordinate)
                && (entityMap.getFromMap(newCoordinate) instanceof Herbivore
                || entityMap.getFromMap(newCoordinate) instanceof Grass
                || entityMap.getFromMap(newCoordinate) == null)
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

    public void findGrass(Coordinates startCoordinates, EntityMap entityMap) {
        reachablePoints.clear();
        previousNodes.clear();
        explored.clear();
        reachablePoints.add(startCoordinates);
        previousNodes.put(startCoordinates, null);
        boolean found = false;
        while (!reachablePoints.isEmpty() && !found) {
            Coordinates node = reachablePoints.poll();
            explored.add(node);

            if (entityMap.getFromMap(node) instanceof Grass) {
                buildPath(node, startCoordinates);
                pathForObject.pop();
                found = true;
            }

            getNewReachablePoints(node, entityMap);
        }
    }


    public Deque<Coordinates> getPathForGrass(Coordinates startCoordinates, EntityMap entityMap){
        findGrass(startCoordinates, entityMap);
        return pathForObject;
    }

    public void findPray(Coordinates startCoordinates, EntityMap entityMap){
        reachablePoints.clear();
        previousNodes.clear();
        explored.clear();
        reachablePoints.add(startCoordinates);
        previousNodes.put(startCoordinates, null);
        while (!reachablePoints.isEmpty()) {
            Coordinates node = reachablePoints.poll();
            explored.add(node);
            if (entityMap.getFromMap(node) instanceof Herbivore) {
                buildPath(node, startCoordinates);
                pathForObject.pop();
                return;
            }
            getNewReachablePoints(node, entityMap);
        }
    }

    public Deque<Coordinates> getPathForPray(Coordinates startCoordinates, EntityMap entityMap){
        findPray(startCoordinates, entityMap);
        return pathForObject;
    }

    public Deque<Coordinates> getLastSteps(){
        return lastSteps;
    }
}