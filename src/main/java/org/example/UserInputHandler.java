package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

// TODO: do something with exceptions that isn't just printing them out
public class UserInputHandler {
    private final BufferedReader reader;
    private final ShapeController controller;

    UserInputHandler() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.controller = new ShapeController();
    }

    public void mainMenu() {
        while (true) {
            System.out.println("========= Main Menu =========");
            System.out.println("What would you like to do?");
            System.out.println("1. Create shape");
            System.out.println("2. List current shapes and groups");
            System.out.println("3. Group shapes");
            System.out.println("4. Move a shape, or a group");
            System.out.println("5. Exit");
            int menuChoice = this.handleIntegerInput();

            switch (menuChoice) {
                case 1:
                    this.createShape();
                    break;
                case 2:
                    this.printCurrentShapesAndGroups();
                    break;
                case 3:
                    this.createGroup();
                    break;
                case 4:
                    this.moveShapeOrGroup();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Please select from the options above.");
            }

            if (menuChoice == 5) {
                break;
            }
        }
    }

    public void createShape() {
        System.out.println("What shape would you like to draw?");
        System.out.println("1: Rectangle");
        System.out.println("2: Circle");

        while (true) {
            int shapeChoice = this.handleIntegerInput();

            switch (shapeChoice) {
                case 1, 2 -> {
                    this.handleCreateShape(shapeChoice);
                    return;
                }
                default -> System.out.println("Please select from the options above.");
            }
        }
    }

    private void handleCreateShape(int choice) {
        System.out.println("I was hoping you'd choose the other one...");
        System.out.println();

        System.out.println("Please provide the X coordinate for your shape:");
        int startingX = this.handleIntegerInput();

        System.out.println("Please provide the Y coordinate for your shape:");
        int startingY = this.handleIntegerInput();

        Shape shape = this.handleChoice(choice, new int[]{startingX, startingY});

        String shapeName = this.askForName("shape");
        this.controller.addShape(shapeName, shape);

        System.out.println("Shape '" + shapeName + "' created!");
        System.out.println();
    }

    private Shape handleChoice(int shapeChoice, int[] startingCoordinates) {
        return switch (shapeChoice) {
            case 1 -> this.handleRectangle(startingCoordinates);
            case 2 -> this.handleCircle(startingCoordinates);
            default -> null;
        };
    }

    private Shape handleRectangle(int[] coordinates) {
        System.out.println("Please provide the length for your rectangle:");
        int length = this.handleIntegerInput();

        System.out.println("Please provide the width for your rectangle:");
        int width = this.handleIntegerInput();

        return new Rectangle(new Point(coordinates[0], coordinates[1]), length, width);
    }

    private Shape handleCircle(int[] coordinates) {
        System.out.println("Please provide the radius for your circle:");
        int radius = this.handleIntegerInput();

        return new Circle(new Point(coordinates[0], coordinates[1]), radius);
    }

    public String askForName(String thingThatNeedsNaming) {
        try {
            System.out.println("Please provide the name for '" + thingThatNeedsNaming + "':");
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

    public void printShapes(Map<String, Shape> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("There are currently no shapes.");
            System.out.println();
        } else {
            for (String shapeName : shapes.keySet()) {
                System.out.println(shapeName);
                this.printCoordinates(this.controller.getCoordinates(shapes.get(shapeName)));
                System.out.println();
            }
        }
    }

    public void printGroups() {
        Map<String, Map<String, Shape>> groups = this.controller.listGroups();

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
        System.out.println("Please provide a comma-separated list of the names of the shapes you want to group.");
        String[] shapeNames = this.handleCommaSeparatedInput();

        // TODO: the controller should be responsible for validating the existence of shapes and groups before creating
        for (String shapeName : shapeNames) {
            try {
                this.controller.getShape(shapeName);
            } catch (IllegalArgumentException iae) {
                System.out.println("Shape '" + shapeName + "' does not exist!");
                return;
            }
        }

        String groupName = this.askForName("group");
        this.controller.createGroup(groupName, shapeNames);
        System.out.println("Created group: " + groupName);
        System.out.println();

    }

    public void moveShapeOrGroup() {
        System.out.println("What would you like to move?");
        System.out.println("1. Shape");
        System.out.println("2. Group");

        while (true) {
            int moveChoice = this.handleIntegerInput();
            switch (moveChoice) {
                case 1, 2 -> {
                    try {
                        this.handleMoveChoice(moveChoice);
                        System.out.println("Consider it done!");
                        System.out.println();
                    } catch (IllegalArgumentException iae) {
                        System.out.println("Invalid move input, got exception: " + iae);
                        System.out.println();
                    }
                    return;
                }
                default -> System.out.println("Please select from the options above.");
            }
        }
    }

    private void handleMoveChoice(int choice) {
        switch (choice) {
            case 1 -> this.moveShape();
            case 2 -> this.moveGroup();
        }
    }

    public void moveShape() throws IllegalArgumentException {
        String shapeName = this.askForName("shape");
        int[] coordinates = this.askWhereToMove();
        try {
            this.controller.moveShape(shapeName, coordinates[0], coordinates[1]);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae);
        }
    }

    public void moveGroup() throws IllegalArgumentException {
        String groupName = this.askForName("group");
        int[] coordinates = this.askWhereToMove();
        try {
            this.controller.moveGroup(groupName, coordinates[0], coordinates[1]);
        } catch (IllegalArgumentException iae) {
            throw new IllegalArgumentException(iae);
        }
    }

    public int[] askWhereToMove() {
        int x = 0, y = 0;
        while (true) {
            try {
                System.out.println("Where would you like to move to?");

                String[] coordinates = this.handleCommaSeparatedInput();

                x = Integer.parseInt(coordinates[0]);
                y = Integer.parseInt(coordinates[1]);

                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter only integers in your comma-separated input.");
            }
        }

        return new int[]{x, y};
    }

    private int handleIntegerInput() {
        try {
            while (true) {
                try {
                    return Integer.parseInt(this.reader.readLine());
                } catch (NumberFormatException nfe) {
                    System.out.println("Please enter a valid number.");
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return 0;
    }

    private String[] handleCommaSeparatedInput() {
        while (true) {
            try {
                String commaSeparatedString = this.reader.readLine();
                String[] coordinates = commaSeparatedString.split(",");
                if (coordinates.length != 2) {
                    System.out.println("Please enter two values separated by a comma.");
                } else {
                    return coordinates;
                }
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }
    }
}
