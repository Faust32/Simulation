package Simulation2D;
import Simulation2D.Entities.EntityName;

import java.util.Scanner;

public class Renderer {
    public void fieldStaticPrinter(EntityMap map) {
        for (int i = 0; i < MapDimension.width - 1; i++) {
            System.out.print("________");
        }
        for (int j = 1; j <= MapDimension.height; j++) {
            System.out.println();
            for (int i = 1; i <= MapDimension.width + 1; i++) {
                if (map.getEntityFromMap(new Coordinates(i, j)) == null) {
                    System.out.print(" |    ");
                } else {
                    EntityName entityName = map.getEntityFromMap(new Coordinates(i, j)).entityName;
                    System.out.print(" |" + entityName.entityName());
                }
            }
        }

        System.out.println();

        for (int i = 0; i < MapDimension.width - 1; i++) {
            System.out.print("--------");
        }

        System.out.println();
    }


    public int greeter(){
        System.out.println("Привет. Это симуляция. Сверху расположена карта с существами. С каждой итерацией они перемещаются к своей цели: для травоядных - трава, для хищников - травоядные.");
        System.out.println("У тебя есть несколько команд:");
        System.out.println("1 - сделать один шаг в симуляции;");
        System.out.println("2 - запустить бесконечную симуляцию;");
        System.out.println("3 - приостановить бесконечную симуляцию;");
        System.out.println("4 - возобновить бесконечную симуляцию;");
        System.out.println("5 - завершить бесконечную симуляцию;");
        System.out.println("6 - завершить программу.");
        System.out.println("Введите 1 или 2 для запуска симуляции или 6, чтобы завершить программу.");
        return startInputScanner();
    }

    public int inputScanner() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean validInputFlag = false;

        while (!validInputFlag) {
            try {
                input = scanner.nextInt();
                if (input < 1 || input > 6) {
                    System.out.println("Я не знаю такой команды. Попробуй еще раз.");
                } else {
                    validInputFlag = true;
                }
            } catch (Exception e) {
                System.out.println("Ты ввел не совсем то, что требовалось. Попробуй еще раз.");
                scanner.nextLine();
            }
        }

        return input;
    }

    public int startInputScanner() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        boolean validInputFlag = false;

        while (!validInputFlag) {
            try {
                input = scanner.nextInt();
                if (input < 1 || input > 6) {
                    System.out.println("Я не знаю такой команды. Попробуй еще раз.");
                }
                if (input > 2 && input < 6) {
                    System.out.println("Симуляция еще не началась. Воспользуйтесь другой командой");
                }
                if (input == 1 || input == 2) {
                    validInputFlag = true;
                }
            } catch (Exception e) {
                System.out.println("Ты ввел не совсем то, что требовалось. Попробуй еще раз.");
                scanner.nextLine();
            }
        }

        return input;
    }
}