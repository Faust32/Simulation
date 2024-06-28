package Package.Actions;

import Package.Coordinates;
import Package.Entities.EntityName;
import Package.Entities.Stationary.Grass;
import Package.EntityMap;

import java.util.concurrent.ThreadLocalRandom;

import static Package.MapDimension.height;
import static Package.MapDimension.width;


public class SpawnGrass extends Action{
    int numberOfGrass = (height * width) / 16;
    public void fillWithGrass(EntityMap entityMap) {
        while (numberOfGrass > 0) {
            int randomCoordinateX = ThreadLocalRandom.current().nextInt(1, height);
            int randomCoordinateY = ThreadLocalRandom.current().nextInt(1, width);
            Coordinates coordinates = new Coordinates(randomCoordinateX, randomCoordinateY);
            if (entityMap.isCellEmpty(coordinates)) {
                entityMap.putInMap(coordinates, new Grass(coordinates, new EntityName("GRSS")));
                numberOfGrass--;
            }
        }
    }

}