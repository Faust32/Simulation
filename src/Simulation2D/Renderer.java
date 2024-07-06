package Simulation2D;

import java.util.Scanner;

public class Renderer {
    public void fieldStaticPrint(EntityMap entityMap) {
        for (int i = 0; i < entityMap.width - 1; i++) {
            System.out.print("________");
        }
        for (int j = 1; j <= entityMap.height; j++) {
            System.out.println();
            for (int i = 1; i <= entityMap.width + 1; i++) {
                if (entityMap.get(new Coordinates(i, j)) == null) {
                    System.out.print(" |    ");
                } else {
                    String entityName = entityMap.get(new Coordinates(i, j)).getEntityName();
                    System.out.print(" |" + entityName);
                }
            }
        }

        System.out.println();

        for (int i = 0; i < entityMap.width - 1; i++) {
            System.out.print("--------");
        }

        System.out.println();
    }


}