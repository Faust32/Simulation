package Package;

public abstract class Creature extends Entity{
    HealthPoints hp;
    MovementSpeed speed;
    public Creature(Coordinates coordinates, EntityName entityName, HealthPoints hp, MovementSpeed speed) {
        super(coordinates, entityName);
        this.hp = hp;
        this.speed = speed;
    }
    abstract void makeMove(Coordinates currentPosition, Map map);
}

