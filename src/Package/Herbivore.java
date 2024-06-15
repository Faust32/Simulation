package Package;

import java.util.*;

public class Herbivore extends Creature{
    public Herbivore(Coordinates coordinates, EntityName entityName, HealthPoints hp, MovementSpeed speed) {
        super(coordinates, entityName, hp, speed);
    }

    private boolean isCoordinateInMap(Coordinates coordinate) {
        if (coordinate.getX() <= 10 && coordinate.getY() <= 10 && coordinate.getX() >= 0 && coordinate.getY() >= 0) {
            return true;
        }
        return false;
    }

    private void getNewReachablePoints(Coordinates node, Queue<Coordinates> reachable, ArrayList<Coordinates> explored, HashMap<Coordinates, Coordinates> previousNodes, Map map) {
        Coordinates right = new Coordinates(node.getX() + 1, node.getY());
        Coordinates left = new Coordinates(node.getX() - 1, node.getY());
        Coordinates up = new Coordinates(node.getX(), node.getY() + 1);
        Coordinates down = new Coordinates(node.getX(), node.getY() - 1);

        if (!explored.contains(right) && !reachable.contains(right) && isCoordinateInMap(right) && (map.getFromMap(right) instanceof Grass || map.getFromMap(right) == null)){
            reachable.add(right);
            previousNodes.put(right, node);
        }
        if (!explored.contains(left) && !reachable.contains(left) && isCoordinateInMap(left) && (map.getFromMap(left) instanceof Grass || map.getFromMap(left) == null)){
            reachable.add(left);
            previousNodes.put(left, node);
        }
        if (!explored.contains(up) && !reachable.contains(up) && isCoordinateInMap(up) && (map.getFromMap(up) instanceof Grass || map.getFromMap(up) == null)){
            reachable.add(up);
            previousNodes.put(up, node);
        }
        if (!explored.contains(down) && !reachable.contains(down) && isCoordinateInMap(down) && (map.getFromMap(down) instanceof Grass || map.getFromMap(down) == null)){
            reachable.add(down);
            previousNodes.put(down, node);
        }
    }

    private Deque<Coordinates> buildPath(Coordinates startCoordinates, Coordinates endCoordinates, HashMap<Coordinates, Coordinates> previousNodes) {
        Deque<Coordinates> path = new LinkedList<Coordinates>();
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

    private Deque<Coordinates> findGrass(Coordinates currentCoordinate, Map map){
        Queue<Coordinates> reachable = new LinkedList<>();
        Deque<Coordinates> pathForGrass = new LinkedList<>();
        ArrayList<Coordinates> explored = new ArrayList<>();
        HashMap<Coordinates, Coordinates> previousNodes = new HashMap<>();
        reachable.add(currentCoordinate);
        while(!reachable.isEmpty()){
            Coordinates node = reachable.poll();
            Entity currentObject = map.getFromMap(node);
            if (currentObject instanceof Entity.Grass){
                pathForGrass = buildPath(currentCoordinate, node, previousNodes);
                break;
            }
            explored.add(node);
            getNewReachablePoints(node, reachable, explored, previousNodes, map);
        }
        return pathForGrass;
    }
    @Override
    public void makeMove(Coordinates currentPosition, Map map){
        Deque<Coordinates> pathForGrass = findGrass(currentPosition, map);
        Entity herbivore = map.getFromMap(currentPosition);
        map.remove(currentPosition);
        if (map.getFromMap(pathForGrass.peek()) instanceof Entity.Grass){
            map.remove(currentPosition);
            map.put(pathForGrass.poll(), herbivore);
        }
        else {
            map.put(pathForGrass.poll(), herbivore);
        }
    }

}
