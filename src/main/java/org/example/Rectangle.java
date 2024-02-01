package org.example;

import java.util.ArrayList;

public class Rectangle extends TwoDimensionalShape {
    private final int length;
    private final int width;


    public Rectangle(int[] bottomLeftCorner, int length, int width) {
        this.startingCoordinates = bottomLeftCorner;
        this.length = length;
        this.width = width;
    }

    public Rectangle(int length, int width) {
        this.startingCoordinates = new int[]{0, 0};
        this.length = length;
        this.width = width;
    }

    public void draw() {
        ArrayList<int[]> coordinates = this.calculateCoordinates();
        for (int[] c : coordinates) {
            System.out.println(c[0] + ", " + c[1]);
        }
    }

    private ArrayList<int[]> calculateCoordinates() {
        int[] bottomRightCorner = new int[]{this.startingCoordinates[0] + length, this.startingCoordinates[1]};
        int[] topLeftCorner = new int[]{this.startingCoordinates[0], this.startingCoordinates[1] + width};
        int[] topRightCorner = new int[]{this.startingCoordinates[0] + length, this.startingCoordinates[1] + width};

        ArrayList<int[]> coordinates = new ArrayList<>();
        coordinates.add(this.startingCoordinates);
        coordinates.add(bottomRightCorner);
        coordinates.add(topLeftCorner);
        coordinates.add(topRightCorner);

        return coordinates;
    }
}
