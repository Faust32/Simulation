package Simulation2D.Actions;

import Simulation2D.Entities.Creature;
import Simulation2D.Entities.Herbivore;
import Simulation2D.CreaturesStatusRender;
import Simulation2D.EntityMap;

import java.util.Set;

public class HungerManager extends Action{
    CreaturesStatusRender statusRender = new CreaturesStatusRender();

    public void startHungerMechanism(EntityMap entityMap){
        Set<Creature> creatures = statusRender.scanCreaturesInMap(entityMap);
        for(Creature creature : creatures){
            if (creature instanceof Herbivore){
                creature.changeHealthPoints(-2);
            }
            else {
                creature.changeHealthPoints(-3);
            }
        }
    }
}
