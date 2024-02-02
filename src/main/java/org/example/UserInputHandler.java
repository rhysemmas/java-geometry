package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

// TODO: consider whether we need an extra layer of abstraction between this input handling class and the shapes.
// Maybe something like a ShapeFactory? Would need to look into exactly how to implement the pattern, but right now
// it feels weird having the thing that just handles input also be responsible for creating and managing the shapes.
public class UserInputHandler {
    private final BufferedReader reader;
    private final ShapeController controller;

    UserInputHandler() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.controller = new ShapeController();
    }

    public void mainMenu() {
        while (true) {
            try {
                System.out.println("========= Main Menu =========");
                System.out.println("What would you like to do?");
                System.out.println("1. Create shape");
                System.out.println("2. List current shapes and groups");
                System.out.println("3. Group shapes");
                System.out.println("4. Move a shape, or a group");
                System.out.println("5. Exit");
                int menuChoice = Integer.parseInt(this.reader.readLine());

                switch (menuChoice) {
                    case 1:
                        createShape();
                        break;
                    case 2:
                        printCurrentShapesAndGroups();
                        break;
                    case 3:
                        createGroup();
                        break;
                    case 4:
                        moveShapeOrGroup();
                        break;
                    case 5:
                        break;
                }

                if (menuChoice == 5) {
                    break;
                }

            } catch (IOException ioe) {
                // TODO: throw exception?
                System.out.println(ioe);
            }
        }
    }

    public void createShape() {
        try {
            System.out.println("What shape would you like to draw?");
            System.out.println("1: Rectangle");
            System.out.println("2: Circle");
            int shapeChoice = Integer.parseInt(this.reader.readLine());

            System.out.println("I was hoping you'd choose the other one...");
            System.out.println();

            System.out.println("Please provide the X coordinate for your shape:");
            int startingX = Integer.parseInt(this.reader.readLine());

            System.out.println("Please provide the Y coordinate for your shape:");
            int startingY = Integer.parseInt(this.reader.readLine());

            Movable shape = handleChoice(shapeChoice, new int[]{startingX, startingY});

            String shapeName = askForName();
            this.controller.addShape(shapeName, shape);

            System.out.println("Shape '" + shapeName + "' created!");
            System.out.println();

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public String askForName() {
        try {
            System.out.println("Please provide the object's name:");
            return this.reader.readLine();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return null;
    }

    public void printCurrentShapesAndGroups() {
        printShapes(this.controller.listShapes());
        printGroups();
    }

    public void printShapes(Map<String, Movable> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("There are currently no shapes.");
            System.out.println();
        } else {
            for (String shapeName : shapes.keySet()) {
                System.out.println(shapeName);
                this.printCoordinates(shapes.get(shapeName).draw());
                System.out.println();
            }
        }
    }

    public void printGroups() {
        Map<String, Map<String, Movable>> groups = this.controller.listGroups();

        if (groups.isEmpty()) {
            System.out.println("There are currently no groups.");
            System.out.println();
        } else {
            for (var groupName : groups.keySet()) {
                System.out.println(groupName);
                this.printShapes(groups.get(groupName));
                System.out.println();
            }
        }
    }

    public void printCoordinates(List<double[]> coordinates) {
        for (double[] coordinate : coordinates) {
            System.out.println("X: " + coordinate[0] + ", " + "Y: " + coordinate[1]);
        }
    }

    public void createGroup() {
        try {
            System.out.println("Please provide a comma-separated list of the names of the shapes you want to group");
            String commaSeparatedString = this.reader.readLine();

            String[] shapeNames = commaSeparatedString.split(",");
            String groupName = this.askForName();

            this.controller.createGroup(groupName, shapeNames);

            System.out.println("Created group: " + groupName);
            System.out.println();

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void moveShapeOrGroup() {
        try {
            System.out.println("What would you like to move?");
            System.out.println("1. Shape");
            System.out.println("2. Group");
            int moveChoice = Integer.parseInt(this.reader.readLine());

            switch (moveChoice) {
                case 1 -> moveShape();
                case 2 -> moveGroup();
            }

            System.out.println("Consider it done!");
            System.out.println();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void moveShape() {
        String shapeName = askForName();
        int[] coordinates = askWhereToMove();
        try {
            this.controller.moveShape(shapeName, coordinates[0], coordinates[1]);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae);
        }
    }

    public void moveGroup() {
        String groupName = askForName();
        int[] coordinates = askWhereToMove();
        try {
            this.controller.moveGroup(groupName, coordinates[0], coordinates[1]);
        } catch (IllegalArgumentException iae) {
            System.out.println(iae);
        }
    }

    public int[] askWhereToMove() {
        try {
            System.out.println("Where would you like to move to?");
            String commaSeparatedString = this.reader.readLine();

            String[] coordinates = commaSeparatedString.split(",");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);

            return new int[]{x, y};

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return null;
    }

    private Movable handleChoice(int shapeChoice, int[] startingCoordinates) {
        Movable shape = null;

        switch (shapeChoice) {
            case 1 -> shape = handleRectangle(startingCoordinates);
            case 2 -> shape = handleCircle(startingCoordinates);
            default -> {/*TODO: throw a shape not found exception?*/}
        }

        return shape;
    }

    private Movable handleRectangle(int[] coordinates) {
        try {
            System.out.println("Please provide the length for your rectangle:");
            int length = Integer.parseInt(this.reader.readLine());

            System.out.println("Please provide the width for your rectangle:");
            int width = Integer.parseInt(this.reader.readLine());

            return new Rectangle(coordinates[0], coordinates[1], length, width);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return null;
    }

    private Movable handleCircle(int[] coordinates) {
        try {
            System.out.println("Please provide the radius for your circle:");
            int radius = Integer.parseInt(this.reader.readLine());

            return new Circle(coordinates[0], coordinates[1], radius);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return null;
    }
}
