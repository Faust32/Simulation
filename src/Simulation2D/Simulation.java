package Simulation2D;

import Simulation2D.Actions.HungerManager;
import Simulation2D.Actions.SpawnGrass;
import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Herbivore;
import Simulation2D.Actions.Action;

import java.util.Scanner;

class Simulation {
    private boolean pause = false;
    private int numberOfIterations = 0;

    Renderer renderer = new Renderer();
    Action action = new Action();
    EntityMap entityMap = new EntityMap();
    CreaturesStatusRender statusRender = new CreaturesStatusRender();
    HungerManager hungerManager = new HungerManager();
    SpawnGrass spawnGrass = new SpawnGrass();


    private void gameModeSelection(int flag) {
        switch (flag){
            case 1: nextTurn(); break;
            case 2: startEndlessMode(); break;
            case 6: exitProgram(); break;
        }

    }

    public void startEverything() {
        entityMap = action.initActions(entityMap);
        renderer.fieldStaticPrinter(entityMap);
        int flag = renderer.greeter();
        gameModeSelection(flag);
    }

    private void proceedIteration() {
        numberOfIterations++;
        if (numberOfIterations % 20 == 0){
            spawnGrass.fillWithGrass(entityMap);
        }
        action.turnActions(entityMap);
        hungerManager.startHungerMechanism(entityMap);
        renderer.fieldStaticPrinter(entityMap);
        statusRender.allCreaturesStatusPrinter(entityMap);
        if (countHerbivoresOnMap() == 0){
            System.out.println("Программа завершилась: травоядных не осталось на карте.");
            System.exit(1);
        }
    }

    private void nextTurn() {
        proceedIteration();
        System.out.println("Нажмите 1, чтобы сделать еще один шаг в симуляции.");
        gameModeSelection(renderer.inputScanner());
    }

    private int countHerbivoresOnMap() {
        int count = 0;
        for (java.util.Map.Entry<Coordinates, Entity> entry : entityMap.entrySet()) {
            Entity entity = entry.getValue();
            if (entity instanceof Herbivore) {
                count++;
            }
        }
        return count;
    }

    private boolean exitSimulation = false;

    private void startEndlessMode() {
        Scanner scanner = new Scanner(System.in);
        Thread inputThread = new Thread(() -> {
            while (true){
                int input;
                boolean validInputFlag = false;
                while (!validInputFlag) {
                    try {
                        input = scanner.nextInt();
                        if (input == 3) {
                            pause = true; // ставлю на паузу
                        } else if (input == 4) {
                            pause = false; // снимаю паузу
                        } else if (input == 5) {
                            exitSimulation = true;
                            return;
                        } else if (input == 6) {
                            exitProgram();
                        } else {
                            System.out.println("Такая команда недоступна во время бесконечной симуляции. " +
                                    "Тебе доступны 4 команды: \n \"3\" - приостановить бесконечную симуляцию, " +
                                    " \"4\" - возобновить бесконечную симуляцию, " +
                                    " \"5\" - завершить бесконечную симуляцию и " +
                                    "\"6\" - завершить программу.");
                        }
                        validInputFlag = true;
                    } catch (Exception e) {
                        System.out.println("Ты ввел не совсем то, что требовалось. Попробуй еще раз.");
                        scanner.nextLine();
                    }
                }
            }
        });

        inputThread.start();

        while (countHerbivoresOnMap() > 0 && !exitSimulation) {
            proceedIteration();
            try {
                Thread.sleep(2000);
                if (pause){
                    System.out.println("Пауза: введите 4 для продолжения.");
                }
                while (pause && !exitSimulation) {
                    // ждем, пока pause не станет false и проверяем ввод
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (exitSimulation) {
            renderer.inputScanner();
        }

    }


    private void exitProgram() {
        System.exit(0);
    }
}
