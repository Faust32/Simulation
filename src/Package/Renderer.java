package Package;
import java.util.HashMap;

public class Renderer {
    public void fieldStaticPrinter(HashMap<Coordinates, Entity> map){
        for (int i = 0; i < MapDimension.height-1; i++) {
            System.out.print("_______");
        }
            for (int j = 1; j <= MapDimension.height; j++) {
            System.out.print("\n");
            for (int i = 1; i <= MapDimension.width+1; i++) {
                if (map.get(new Coordinates(i, j)) == null) System.out.printf(" |    ");
                else {
                    EntityName a = map.get(new Coordinates(i, j)).entityName;
                    System.out.printf(" |" + a.getEntityName());
                }
            }

        }
        System.out.print("\n");
            for (int i = 0; i < MapDimension.height-1; i++) {
                System.out.print("-------");
            }
        System.out.println();
    }

    public void greeter(){
        System.out.println("Привет. Это симуляция. ");
    }

}