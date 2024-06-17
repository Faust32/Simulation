package Package;

import java.util.HashMap;

class Simulation {
    Renderer renderer = new Renderer();
    Actions action = new Actions();
    HashMap<Coordinates, Entity> map = action.initActions();

    public void simulationGreeter(){
        renderer.greeter();
        renderer.fieldStaticPrinter(map);
    }

    public void nextTurn(){
        action.turnActions();
        renderer.fieldStaticPrinter(map);
    }

    public void startSimulation(){

    }

    public void pauseSimulation(){

    }


    public static void main(String[] args) {
        Simulation s = new Simulation();
        s.simulationGreeter();
        s.nextTurn();
    }
}
