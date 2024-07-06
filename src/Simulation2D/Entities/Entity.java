package Simulation2D.Entities;
import Simulation2D.Coordinates;


abstract public class Entity {
    protected String entityName;
    public String getEntityName() {
        return entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}

