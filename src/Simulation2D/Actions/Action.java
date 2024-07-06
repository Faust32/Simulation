package Simulation2D.Actions;

import Simulation2D.Entities.*;
import Simulation2D.EntityMap;
import Simulation2D.CreaturesStatusRender;

import java.util.Set;


public abstract class Action {
    public abstract void perform(EntityMap entityMap);
}