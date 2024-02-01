package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// TODO: consider whether we need an extra layer of abstraction between this input handling class and the shapes.
// Maybe something like a ShapeFactory? Would need to look into exactly how to implement the pattern, but right now
// it feels weird having the thing that just handles input also be responsible for creating and managing the shapes.
public class UserInputHandler {
    private final BufferedReader reader;
    private final HashMap<String, TwoDimensionalShape> shapes;
    private final HashMap<String, GroupedShape> groups;

    UserInputHandler() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.shapes = new HashMap<>();
        this.groups = new HashMap<>();
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

            System.out.println("Please provide the X coordinate for your shape:");
            int startingX = Integer.parseInt(this.reader.readLine());

            System.out.println("Please provide the Y coordinate for your shape:");
            int startingY = Integer.parseInt(this.reader.readLine());

            TwoDimensionalShape shape = handleChoice(shapeChoice, new int[]{startingX, startingY});

            String shapeName = askForName();
            this.shapes.put(shapeName, shape);
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
        printShapes();
        printGroups();
    }

    public void printShapes() {
        if (this.shapes.size() < 1) {
            System.out.println("There are currently no shapes.");
            System.out.println();
        } else {
            for (var shape : this.shapes.entrySet()) {
                System.out.println(shape.getKey());
                shape.getValue().draw();
                System.out.println();
            }
        }
    }

    public void printGroups() {
        if (this.groups.size() < 1) {
            System.out.println("There are currently no groups.");
            System.out.println();
        } else {
            for (var group : this.groups.entrySet()) {
                System.out.println(group.getKey());
                group.getValue().draw();
                System.out.println();
            }
        }
    }

    public void createGroup() {
        try {
            System.out.println("Please provide a comma-separated list of the names of the shapes you want to group");
            String commaSeparatedString = this.reader.readLine();
            String[] shapeNames = commaSeparatedString.split(",");

            TwoDimensionalShape[] shapes = new TwoDimensionalShape[shapeNames.length];

            int i = 0;
            for (String name : shapeNames) {
                TwoDimensionalShape shapeToGroup = this.shapes.get(name);
                shapes[i] = shapeToGroup;

                this.shapes.remove(name);

                i++;
            }

            String groupName = this.askForName();

            GroupedShape group = new GroupedShape(shapes);
            groups.put(groupName, group);

            System.out.println("Created group: " + groupName);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void moveShapeOrGroup() {
        try {
            System.out.println("What would you like to move?");
            System.out.println("1. Shape");
            System.out.println("2. Group");
            System.out.println();
            int moveChoice = Integer.parseInt(this.reader.readLine());

            switch (moveChoice) {
                case 1 -> moveShape();
                case 2 -> moveGroup();
            }

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void moveShape() {
        String shapeName = askForName();
        TwoDimensionalShape shape = shapes.get(shapeName);
        move(shape);
    }

    public void moveGroup() {
        String groupName = askForName();
        GroupedShape group = groups.get(groupName);
        move(group);
    }

    public void move(TwoDimensionalShape shape) {
        try {
            System.out.println("Which direction would you like to move?");
            System.out.println("1. Left");
            System.out.println("2. Right");
            System.out.println("3. Up");
            System.out.println("4. Down");
            System.out.println();
            int directionChoice = Integer.parseInt(this.reader.readLine());

            System.out.println("And by how many units would you like to move?");
            int offsetChoice = Integer.parseInt(this.reader.readLine());

            switch (directionChoice) {
                case 1 -> shape.moveLeft(offsetChoice);
                case 2 -> shape.moveRight(offsetChoice);
                case 3 -> shape.moveUp(offsetChoice);
                case 4 -> shape.moveDown(offsetChoice);
            }

            System.out.println("Consider it done!");
            System.out.println();

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    private TwoDimensionalShape handleChoice(int shapeChoice, int[] startingCoordinates) {
        TwoDimensionalShape shape = null;

        switch (shapeChoice) {
            case 1 -> shape = handleRectangle(startingCoordinates);
            case 2 -> shape = handleCircle(startingCoordinates);
            default -> {/*TODO: throw a shape not found exception?*/}
        }

        return shape;
    }

    private TwoDimensionalShape handleRectangle(int[] coordinates) {
        try {
            System.out.println("Please provide the length for your rectangle:");
            int length = Integer.parseInt(this.reader.readLine());

            System.out.println("Please provide the width for your rectangle:");
            int width = Integer.parseInt(this.reader.readLine());

            return new Rectangle(coordinates, length, width);
        } catch (IOException ioe) {
            // TODO: throw exception?
            System.out.println(ioe);
        }

        return null;
    }

    private TwoDimensionalShape handleCircle(int[] coordinates) {
        try {
            System.out.println("Please provide the radius for your circle:");
            int radius = Integer.parseInt(this.reader.readLine());

            return new Circle(coordinates, radius);
        } catch (IOException ioe) {
            // TODO: throw exception?
            System.out.println(ioe);
        }

        return null;
    }
}
