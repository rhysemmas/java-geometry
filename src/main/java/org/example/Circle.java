package org.example;

public class Circle extends Point {
    private final int radius;

    public Circle(int x, int y, int radius) {
        this.coordinate = new double[]{x, y};
        this.radius = radius;
    }

    public double area() {
        return Math.PI * (radius * radius);
    }
}
