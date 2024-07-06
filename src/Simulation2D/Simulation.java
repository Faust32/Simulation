package Simulation2D;

import Simulation2D.Actions.*;
import Simulation2D.Actions.HungerManager;
import Simulation2D.Actions.SpawnGrass;
import Simulation2D.Entities.Entity;
import Simulation2D.Entities.Herbivore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    private boolean pause = false;
    private boolean exitSimulation = false;
    private final Renderer renderer = new Renderer();
    private final EntityMap entityMap;
    private final CreaturesStatusRender statusRender = new CreaturesStatusRender();
    private final HungerManager hungerManager = new HungerManager();
    private final InputHandler inputHandler = new InputHandler();
    private final List<Action> initActions;
    private final List<Action> turnActions;

    public Simulation(EntityMap entityMap) {
        this.entityMap = entityMap;
        this.turnActions = new ArrayList<>();
        this.initActions = new ArrayList<>();
    }

    public void createAction(EntityMap entityMap) {
        initActions.add(new SpawnRock(entityMap));
        initActions.add(new SpawnGrass(entityMap));
        initActions.add(new SpawnTree(entityMap));
        initActions.add(new SpawnPredator(entityMap));
        initActions.add(new SpawnHerbivore(entityMap));
        turnActions.add(new SpawnGrass(entityMap));
        turnActions.add(new MoveCreatures());
    }

    public int greet(){
        System.out.println("Привет. Это симуляция. Сверху расположена карта с существами. С каждой итерацией они перемещаются к своей цели: для травоядных - трава, для хищников - травоядные.");
        System.out.println("У тебя есть несколько команд:");
        System.out.println("1 - сделать один шаг в симуляции;");
        System.out.println("2 - запустить бесконечную симуляцию;");
        System.out.println("3 - приостановить бесконечную симуляцию;");
        System.out.println("4 - возобновить бесконечную симуляцию;");
        System.out.println("5 - завершить бесконечную симуляцию;");
        System.out.println("6 - завершить программу.");
        System.out.println("Введите 1 или 2 для запуска симуляции или 6, чтобы завершить программу.");
        return inputHandler.scannerOnStart();
    }

    private void gameModeSelection(int flag) {
        switch (flag){
            case 1: nextTurn(); break;
            case 2: startEndlessMode(); break;
            case 6: exitProgram(); break;
        }
    }

    public void launch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в симуляцию. Введи 1, чтобы запустить программу и 6, чтобы ее завершить.");
        int input = inputHandler.checkValidity(scanner);
        if (input == 1) {
            startEverything();
        }
        else if (input == 6) {
            exitProgram();
        }
    }

    public void startEverything() {
        createAction(entityMap);
        for (final Action action : initActions) {
            action.perform(entityMap);
        }
        renderer.fieldStaticPrint(entityMap);
        int flag = greet();
        gameModeSelection(flag);
    }

    private void proceedIteration() {
        for(Action action : turnActions) {
            action.perform(entityMap);
        }
        hungerManager.startHunger(entityMap);
        renderer.fieldStaticPrint(entityMap);
        statusRender.allCreaturesStatusPrint(entityMap);
        if (countHerbivoresOnMap() == 0){
            System.out.println("Программа завершилась: травоядных не осталось на карте.");
            System.exit(1);
        }
    }

    private void nextTurn() {
        proceedIteration();
        System.out.println("Нажмите 1, чтобы сделать еще один шаг в симуляции.");
        gameModeSelection(inputHandler.casualScanner());
    }

    private int countHerbivoresOnMap() {
        int count = 0;
        for (java.util.Map.Entry<Coordinates, Entity> entry : entityMap.getEntries()) {
            Entity entity = entry.getValue();
            if (entity instanceof Herbivore) {
                count++;
            }
        }
        return count;
    }

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
            inputHandler.casualScanner();
        }

    }

    private void exitProgram() {
        System.exit(0);
    }
}
