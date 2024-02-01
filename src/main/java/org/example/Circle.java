package org.example;

import java.util.ArrayList;

public class Circle extends TwoDimensionalShape {
    private final int radius;

    public Circle(int[] centre, int radius) {
        this.startingCoordinate = centre;
        this.radius = radius;
    }

    public Circle(int radius) {
        this.startingCoordinate = new int[]{0, 0};
        this.radius = radius;
    }

    public double area() {
        return Math.PI * (radius * radius);
    }

    public ArrayList<int[]> draw() {
        // TODO: how do you represent a drawn circle with coordinates?
        ArrayList<int[]> coordinates = new ArrayList<>();
        coordinates.add(this.startingCoordinate);

        return coordinates;
    }


}
