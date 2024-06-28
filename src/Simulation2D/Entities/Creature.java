package Simulation2D.Entities;
import Simulation2D.Coordinates;
import Simulation2D.EntityMap;


public abstract class Creature extends Entity {
    protected int healthPoints;
    protected int movementSpeed;
    public Creature(Coordinates coordinates, EntityName entityName) {
        super(coordinates, entityName);

    }

    public String getCreatureName() {
        return entityName.entityName();
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public String getStringCoordinates(){
        return coordinates.getStringCoordinates();
    }

    public boolean isDead() {
        return healthPoints <= 0;
    }

    public void changeHealthPoints(int value) {
        this.healthPoints += value;
    }

    public abstract void makeMove(Coordinates currentPosition, EntityMap entityMap);
}

