package Package;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Herbivore extends Creature{

    private Map map;

    public Herbivore(Coordinates coordinates, EntityName name, HealthPoints hp, MovementSpeed speed) {
        this.coordinates = coordinates;
        this.entityName = name;
        this.hp = hp;
        this.speed = speed;
    }


    private void getNewReachablePoints(Coordinates node, Queue<Coordinates> reachable, ArrayList<Coordinates> explored) {
        Coordinates right = new Coordinates(node.getX() + 1, node.getY());
        Coordinates left = new Coordinates(node.getX() - 1, node.getY());
        Coordinates up = new Coordinates(node.getX(), node.getY() + 1);
        Coordinates down = new Coordinates(node.getX(), node.getY() - 1);

        if (!explored.contains(right) && !reachable.contains(right)) {
            reachable.add(right);
        }
        if (!explored.contains(left) && !reachable.contains(left)) {
            reachable.add(left);
        }
        if (!explored.contains(up) && !reachable.contains(up)) {
            reachable.add(up);
        }
        if (!explored.contains(down) && !reachable.contains(down)) {
            reachable.add(down);
        }
    }


    private Queue<Coordinates> buildPath(Coordinates startCoordinates, Coordinates endCoordinates) {
        Queue<Coordinates> path = new LinkedList<Coordinates>();
        path.add(startCoordinates);

        return path;
    }

    private void findGrass(Coordinates currentCoordinate){
        Queue<Coordinates> reachable = new LinkedList<>();
        ArrayList<Coordinates> explored = new ArrayList<>();
        reachable.add(currentCoordinate);
        while(!reachable.isEmpty()){
            Coordinates node = reachable.poll();
            Entity currentObject = map.getFromMap(node);
            if (currentObject instanceof Entity.Grass){
                buildPath(currentCoordinate, node);
                return;
            }
            explored.add(node);
            getNewReachablePoints(node, reachable, explored);
        }
    }

    public void makeMove(){

    }

    private void eatGrass(){

    }


}
