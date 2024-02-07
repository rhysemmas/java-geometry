package org.example;

public class Circle implements Shape {
    private final int radius;
    private final Movable center;

    public Circle(Movable center, int radius) {
        this.radius = radius;
        this.center = center;
    }

    public Movable[] getCorners() throws HasNoCornersException {
        throw new HasNoCornersException("Circle has no corners");
    }

    public Movable getCenter() {
        return this.center;
    }
}
