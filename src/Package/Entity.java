package Package;

abstract public class Entity {
    Coordinates coordinates;
    EntityName entityName;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    static class Grass extends Entity {
        public Grass(Coordinates coordinates, EntityName name) {
            this.coordinates = coordinates;
            this.entityName = name;
        }
    }
    static class Rock extends Entity {
        public Rock(Coordinates coordinates, EntityName name) {
            this.coordinates = coordinates;
            this.entityName = name;

        }
    }
    static class Tree extends Entity {
        public Tree(Coordinates coordinates, EntityName name) {
            this.coordinates = coordinates;
            this.entityName = name;
        }
    }

}

