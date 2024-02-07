package org.example;

public class Point implements Movable {
    private double[] coordinate;

    public Point(double x, double y) {
        this.move(x, y);
    }

    public void move(double x, double y) {
        this.coordinate = new double[]{x, y};
    }

    public double[] getPosition() {
        return this.coordinate;
    }
}
