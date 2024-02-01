package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        /* input:
                - hello
                - what would you like to draw
                - 1. circle, 2. square
                - *circle*
                - what radius is your circle
                - *3*
                - what is your circle's centre point (default 0,0)
                - *0,0*
                - ok, here is your circle: *print coords*
         */

        // Main code calls input handler to ask user to create shape
        // Then repeats loop until user decides they are done creating shapes
        // Then exposes shapes to user and asks if they'd like to perform operations on them
        // (store shapes in a map and let user select based on key?)
        // Operations will be things like move and just call the underlying move method on the TwoDimensionalShape.
        //
        // Another operation - group - will allow user to group shapes together (add them to a linked list inside the
        // map?) and then user can move the group. Either using a unique identifier for the group, or maybe if they
        // move one shape which is part of a group, the entire group moves with it.
        HashMap<String, TwoDimensionalShape> namedShapes = new HashMap<>();
        UserInputHandler handler = new UserInputHandler();

        handler.mainMenu();

//        TwoDimensionalShape shape = handler.askWhichShape();
//        String shapeName = handler.askForShapeName();
//        namedShapes.put(shapeName, shape);
//        shape.draw();
//
//        TwoDimensionalShape test = namedShapes.get("hello");
//        if (test == null) {
//            System.out.println("No such shape named: " + "hello");
//        }
    }
}