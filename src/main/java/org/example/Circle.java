package org.example;

public class Circle extends TwoDimensionalShape {
    private final int radius;

    public Circle(int[] centre, int radius) {
        this.startingCoordinates = centre;
        this.radius = radius;
    }

    public Circle(int radius) {
        this.startingCoordinates = new int[]{0, 0};
        this.radius = radius;
    }

    public double area() {
        return Math.PI * (radius * radius);
    }
}
