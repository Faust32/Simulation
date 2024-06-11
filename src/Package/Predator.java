package Package;

import java.util.*;

public class Predator extends Creature{
    private Map map;
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



    private void getNewReachablePoints(Coordinates node, Queue<Coordinates> reachable, ArrayList<Coordinates> explored, HashMap<Coordinates, Coordinates> previousNodes) {
        Coordinates right = new Coordinates(node.getX() + 1, node.getY());
        Coordinates left = new Coordinates(node.getX() - 1, node.getY());
        Coordinates up = new Coordinates(node.getX(), node.getY() + 1);
        Coordinates down = new Coordinates(node.getX(), node.getY() - 1);

        if (!explored.contains(right) && !reachable.contains(right)) {
            reachable.add(right);
            previousNodes.put(right, node);
        }
        if (!explored.contains(left) && !reachable.contains(left)) {
            reachable.add(left);
            previousNodes.put(left, node);
        }
        if (!explored.contains(up) && !reachable.contains(up)) {
            reachable.add(up);
            previousNodes.put(up, node);
        }
        if (!explored.contains(down) && !reachable.contains(down)) {
            reachable.add(down);
            previousNodes.put(down, node);
        }
    }


    private Deque<Coordinates> buildPath(Coordinates startCoordinates, Coordinates endCoordinates, HashMap<Coordinates, Coordinates> previousNodes) {
        Deque<Coordinates> path = new LinkedList<Coordinates>();
        path.add(endCoordinates);
        while(!previousNodes.containsKey(endCoordinates)) {
            path.add(previousNodes.get(endCoordinates));
            endCoordinates = previousNodes.get(endCoordinates);
        }
        return path;
    }

    private Deque<Coordinates> findPray(Coordinates currentCoordinate){
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
            getNewReachablePoints(node, reachable, explored, previousNodes);
        }
        return pathForPray;
    }

    @Override
    public Coordinates makeMove(Coordinates currentPosition){
        Deque<Coordinates> pathForPray = findPray(currentPosition);
        return pathForPray.pollLast();
    }

}
