package Package;

public class Predator extends Creature{
    private final int attackPower;
    public Predator(int attackPower){
        this.attackPower = attackPower;
    }

    public Predator(Coordinates coord, EntityName entityName, HealthPoints healthPoints, MovementSpeed movementSpeed, int attackPower) {
        this.coordinates = coord;
        this.entityName = entityName;
        this.hp = healthPoints;
        this.speed = movementSpeed;
        this.attackPower = attackPower;
    }

    public void makeMove(){

    }

    private void attackHerbivore(){

    }

    public int getAttackPower() {
        return attackPower;
    }
}
