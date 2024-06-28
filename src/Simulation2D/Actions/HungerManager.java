package Package.Actions;

import Package.Entities.Creature;
import Package.Entities.Herbivore;
import Package.CreaturesStatusRender;
import Package.EntityMap;

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
