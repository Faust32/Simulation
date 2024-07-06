package Simulation2D.Actions;

import Simulation2D.CreaturesStatusRender;
import Simulation2D.Entities.Creature;
import Simulation2D.EntityMap;

import java.util.Set;


public class MoveCreatures extends Action {
    CreaturesStatusRender statusRender = new CreaturesStatusRender();
    @Override
    public void perform(EntityMap entityMap) {
        Set<Creature> creatures = statusRender.scanCreaturesInMap(entityMap);
        for (Creature creature : creatures) {
            if(!entityMap.containsEntity(creature)){
                continue;
            }
            for (int i = 0; i < creature.getMovementSpeed(); i++) {
                creature.makeMove(creature.getCoordinates(), entityMap);
            }
        }
    }
}
