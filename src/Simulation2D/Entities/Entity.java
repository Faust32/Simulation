package Package.Entities;
import Package.Coordinates;


abstract public class Entity {
    Coordinates coordinates;
    public EntityName entityName;

    public Entity(Coordinates coordinates, EntityName entityName) {
        this.coordinates = coordinates;
        this.entityName = entityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    protected void updateCoordinates(Coordinates newCoordinates) {
        coordinates = newCoordinates;
    }

}

