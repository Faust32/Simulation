package Package;

abstract public class Entity {
    Coordinates coordinates;
    EntityName entityName;

    public Entity(Coordinates coordinates, EntityName entityName) {
        this.coordinates = coordinates;
        this.entityName = entityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    static class Grass extends Entity {
        public Grass(Coordinates coordinates, EntityName entityName) {
            super(coordinates, entityName);
        }
    }
    static class Rock extends Entity {
        public Rock(Coordinates coordinates, EntityName entityName) {
            super(coordinates, entityName);

        }
    }
    static class Tree extends Entity {
        public Tree(Coordinates coordinates, EntityName entityName) {
            super(coordinates, entityName);
        }
    }

}

