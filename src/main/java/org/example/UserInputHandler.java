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
    private HashMap<String, TwoDimensionalShape> shapes;

    UserInputHandler() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void mainMenu() {
        while (true) {
            try {
                System.out.println("========= Main Menu =========");
                System.out.println("What would you like to do?");
                System.out.println("1. Create shape");
                System.out.println("2. List current shapes");
                System.out.println("3. Group shapes");
                System.out.println("4. Move a shape, or a group");
                System.out.println("5. Exit");
                int menuChoice = Integer.parseInt(this.reader.readLine());

                switch (menuChoice) {
                    case 1:
                        TwoDimensionalShape shape = askWhichShape();
                    case 2:
                        // TODO: print current list of shapes
                    case 3:
                        // TODO: group shapes and add group to list, remove them from shape list
                    case 4:
                        // TODO: ask for input on which shape or group to move
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

    public TwoDimensionalShape askWhichShape() {
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

            return handleChoice(shapeChoice, new int[]{startingX, startingY});

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return null;
    }

    public String askForShapeName() {
        try {
            System.out.println("What is the name of this shape?");
            return this.reader.readLine();

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return null;
    }

    private TwoDimensionalShape handleChoice(int shapeChoice, int[] startingCoordinates) {
        TwoDimensionalShape shape = null;

        switch (shapeChoice) {
            case 1:
                shape = handleRectangle(startingCoordinates);
                break;
            case 2:
                shape = handleCircle(startingCoordinates);
                break;
            default:
                // TODO: throw a shape not found exception?
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
