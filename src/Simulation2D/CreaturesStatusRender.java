package Simulation2D;

import Simulation2D.Entities.Creature;
import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Herbivore;
import Simulation2D.Entities.Predator;

import java.util.HashSet;
import java.util.Set;

public class CreaturesStatusRender extends Renderer{
    private final Set<Creature> creatures = new HashSet<>();

    private void herbivoreStatusPrint(EntityMap entityMap){
        scanCreaturesInMap(entityMap);
        for (Creature creature : creatures){
            if (creature instanceof Herbivore){
                System.out.println("Состояние травоядного" + " \"" + creature.getCreatureName() +
                        "\" на координатах {" + creature.getCoordinates().getX() + " "
                        + creature.getCoordinates().getY() + "}. HP: " + creature.getHealthPoints()
                        + ", скорость передвижения: " + creature.getMovementSpeed());
            }
        }
    }

    private void predatorStatusPrint(EntityMap entityMap){
        scanCreaturesInMap(entityMap);
        for (Creature creature : creatures){
            if (creature instanceof Predator){
                System.out.println("Состояние хищника" + " \"" + creature.getCreatureName() +
                        " на координатах {" + creature.getCoordinates().getX() + " " +
                        + creature.getCoordinates().getY() + "}. HP: " + creature.getHealthPoints() +
                        ", сила атаки: " + ((Predator) creature).getAttackDamage() +
                        ", скорость передвижения: " + creature.getMovementSpeed());
            }
        }
    }

    public void allCreaturesStatusPrint(EntityMap entityMap){
        herbivoreStatusPrint(entityMap);
        predatorStatusPrint(entityMap);
    }

    public Set<Creature> scanCreaturesInMap(EntityMap entityMap){
        creatures.clear();
        for (java.util.Map.Entry<Coordinates, Entity> entry : entityMap.getEntries()) {
            Entity entity = entry.getValue();
            if (entity instanceof Herbivore || entity instanceof Predator) {
                creatures.add((Creature)entity);
            }
        }
        return creatures;
    }
}
