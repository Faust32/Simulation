package Package;

import Package.Entities.Creature;
import Package.Entities.Entity;
import Package.Entities.Herbivore;
import Package.Entities.Predator;

import java.util.HashSet;
import java.util.Set;

public class CreaturesStatusRender extends Renderer{

    Set<Creature> creatures = new HashSet<>();

    private void herbivoreStatusPrinter(EntityMap entityMap){
        scanCreaturesInMap(entityMap);
        for (Creature creature : creatures){
            if (creature instanceof Herbivore){
                System.out.println("Состояние травоядного" + " \"" + creature.getCreatureName() +
                        "\" на координатах " + creature.getStringCoordinates() + ". HP: " + creature.getHealthPoints()
                        + ", скорость передвижения: " + creature.getMovementSpeed());
            }
        }
    }

    private void predatorStatusPrinter(EntityMap entityMap){
        scanCreaturesInMap(entityMap);
        for (Creature creature : creatures){
            if (creature instanceof Predator){
                System.out.println("Состояние хищника" + " \"" + creature.getCreatureName() +
                        "\" на координатах " + creature.getStringCoordinates() + ". HP: " +
                        creature.getHealthPoints() + ", сила атаки: " + ((Predator) creature).getAttackDamage() +
                        ", скорость передвижения: " + creature.getMovementSpeed());
            }
        }
    }

    public void allCreaturesStatusPrinter(EntityMap entityMap){
        herbivoreStatusPrinter(entityMap);
        predatorStatusPrinter(entityMap);
    }

    public Set<Creature> scanCreaturesInMap(EntityMap entityMap){
        creatures.clear();
        for (java.util.Map.Entry<Coordinates, Entity> entry : entityMap.entrySet()) {
            Entity entity = entry.getValue();
            if (entity instanceof Herbivore || entity instanceof Predator) {
                creatures.add((Creature)entity);
            }
        }
        return creatures;
    }
}
