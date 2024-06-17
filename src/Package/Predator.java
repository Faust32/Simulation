package Package;

import java.util.*;

public class Predator extends Creature{
    private final int attackPower;
    public int getAttackPower() {
        return attackPower;
    }


    public Predator(Coordinates coordinates, EntityName entityName, HealthPoints healthPoints, MovementSpeed movementSpeed, int attackPower) {
        super(coordinates, entityName, healthPoints, movementSpeed);
        this.attackPower = attackPower;
    }

    private void attackHerbivore(){

    }

    private boolean isCoordinateInMap(Coordinates coordinate) {
        if (coordinate.getX() <= 10 && coordinate.getY() <= 10 && coordinate.getX() > 0 && coordinate.getY() > 0) {
            return true;
        }
        return false;
    }


    private void getNewReachablePoints(Coordinates node, Queue<Coordinates> reachable, ArrayList<Coordinates> explored, HashMap<Coordinates, Coordinates> previousNodes, Map map) {
        Coordinates right = new Coordinates(node.getX() + 1, node.getY());
        Coordinates left = new Coordinates(node.getX() - 1, node.getY());
        Coordinates up = new Coordinates(node.getX(), node.getY() + 1);
        Coordinates down = new Coordinates(node.getX(), node.getY() - 1);

        if (!explored.contains(right) && !reachable.contains(right) && isCoordinateInMap(right) && (map.getFromMap(right) instanceof Herbivore || map.getFromMap(right) == null)){
            reachable.add(right);
            previousNodes.put(right, node);
        }
        if (!explored.contains(left) && !reachable.contains(left) && isCoordinateInMap(left) && (map.getFromMap(left) instanceof Herbivore || map.getFromMap(left) == null)){
            reachable.add(left);
            previousNodes.put(left, node);
        }
        if (!explored.contains(up) && !reachable.contains(up) && isCoordinateInMap(up) && (map.getFromMap(up) instanceof Herbivore || map.getFromMap(up) == null)){
            reachable.add(up);
            previousNodes.put(up, node);
        }
        if (!explored.contains(down) && !reachable.contains(down) && isCoordinateInMap(down) && (map.getFromMap(down) instanceof Herbivore || map.getFromMap(down) == null)){
            reachable.add(down);
            previousNodes.put(down, node);
        }
    }


    private Deque<Coordinates> buildPath(Coordinates startCoordinates, Coordinates endCoordinates, HashMap<Coordinates, Coordinates> previousNodes) {
        Deque<Coordinates> path = new LinkedList<>();
        Coordinates current = endCoordinates;
        while (current != null && previousNodes.containsKey(current)) {
            path.addFirst(current);
            current = previousNodes.get(current);
        }
        if (current != null) {
            path.addFirst(startCoordinates);
        }
        path.pop();
        return path;
    }

    private Deque<Coordinates> findPray(Coordinates currentCoordinate, Map map){
        Queue<Coordinates> reachable = new LinkedList<>();
        Deque<Coordinates> pathForPray = new LinkedList<>();
        ArrayList<Coordinates> explored = new ArrayList<>();
        HashMap<Coordinates, Coordinates> previousNodes = new HashMap<>();
        reachable.add(currentCoordinate);
        while(!reachable.isEmpty()){
            Coordinates node = reachable.poll();
            Entity currentObject = map.getFromMap(node);
            if (currentObject instanceof Herbivore){
                pathForPray = buildPath(currentCoordinate, node, previousNodes);
            }
            explored.add(node);
            getNewReachablePoints(node, reachable, explored, previousNodes, map);
        }
        return pathForPray;
    }

    @Override
    public void makeMove(Coordinates currentPosition, Map map){
        Deque<Coordinates> pathForPray = findPray(currentPosition, map);
        if (pathForPray == null || pathForPray.isEmpty()) {
            // Handle the case where no path to grass is found
            return;
        }
        Entity predator = map.getFromMap(currentPosition);
        if (predator == null) {
            // Handle the case where there is no herbivore at the current position
            return;
        }
        Coordinates positionToMove = pathForPray.poll();
        if (map.getFromMap(positionToMove) instanceof Herbivore){
            map.remove(positionToMove);
            predator.updateCoordinates(positionToMove);
            map.put(positionToMove, predator);
            map.remove(currentPosition);
        }
        else {
            predator.updateCoordinates(positionToMove);
            map.put(positionToMove, predator);
            map.remove(currentPosition);
        }
    }

}
