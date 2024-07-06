package Simulation2D;

import java.util.Scanner;

public class InputHandler {
    public int checkValidity(Scanner scanner) {
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

    public int casualScanner() {
        Scanner scanner = new Scanner(System.in);
        return checkValidity(scanner);
    }

    public int scannerOnStart() {
        Scanner scanner = new Scanner(System.in);
        int input = checkValidity(scanner);
        if (input > 2 && input < 6) {
            System.out.println("Симуляция еще не началась. Воспользуйся другой командой.");
            casualScanner();
        }
        return input;
    }
}
