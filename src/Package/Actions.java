package Package;
import java.util.concurrent.ThreadLocalRandom;

public class Actions {
    public void initActions(){
        Map map = new Map();
        map.fillMapRandomly();
    }

    public void turnActions(){

    }

}

class MainPrikol{
    public static void main(String[] args) {
        int i = 0;
        Actions t = new Actions();
        t.initActions();
    }
}
