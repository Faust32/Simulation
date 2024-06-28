package Simulation2D.Actions;

import Simulation2D.Coordinates;
import Simulation2D.Entities.EntityName;
import Simulation2D.Entities.Stationary.Tree;
import Simulation2D.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

import static Simulation2D.MapDimension.height;
import static Simulation2D.MapDimension.width;


public class SpawnTree extends Action {
    private int numberOfTrees = (height * width) / 16;
    public void fillWithTrees(EntityMap entityMap) {
        while (numberOfTrees > 0) {
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, height);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, width);
            Coordinates coordinates = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (entityMap.isCellEmpty(coordinates)) {
                entityMap.putInMap(coordinates, new Tree(coordinates, new EntityName("TREE")));
                numberOfTrees--;
            }
        }
    }

}