package Package.Actions;

import Package.Coordinates;
import Package.Entities.EntityName;
import Package.Entities.Stationary.Tree;
import Package.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

import static Package.MapDimension.height;
import static Package.MapDimension.width;


public class SpawnTree extends Action {
    int numberOfTrees = (height * width) / 16;
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