package Simulation2D;

public class Main {
    public static void main(String[] args) {
        Simulation s = new Simulation(new EntityMap(10, 10));
        s.launch();
    }
}
