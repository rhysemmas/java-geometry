package org.example;

import java.util.ArrayList;

public class Rectangle extends TwoDimensionalShape {
    private final int length;
    private final int width;

    public Rectangle(int[] bottomLeftCorner, int length, int width) {
        this.startingCoordinate = bottomLeftCorner;
        this.length = length;
        this.width = width;
    }

    public Rectangle(int length, int width) {
        this.startingCoordinate = new int[]{0, 0};
        this.length = length;
        this.width = width;
    }

    public ArrayList<int[]> draw() {
        int[] bottomRightCorner = new int[]{this.startingCoordinate[0] + length, this.startingCoordinate[1]};
        int[] topLeftCorner = new int[]{this.startingCoordinate[0], this.startingCoordinate[1] + width};
        int[] topRightCorner = new int[]{this.startingCoordinate[0] + length, this.startingCoordinate[1] + width};

        ArrayList<int[]> coordinates = new ArrayList<>();
        coordinates.add(this.startingCoordinate);
        coordinates.add(bottomRightCorner);
        coordinates.add(topLeftCorner);
        coordinates.add(topRightCorner);

        return coordinates;
    }
}
