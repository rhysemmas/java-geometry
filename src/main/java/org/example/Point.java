package org.example;

import java.util.ArrayList;
import java.util.List;

public class Point implements Movable {
    public double[] coordinate;

    public Point() {
    }

    public void move(double x, double y) {
        this.coordinate = new double[]{x, y};
    }

    public double[] getPoint() {
        return this.coordinate;
    }

    public List<double[]> draw() {
        ArrayList<double[]> position = new ArrayList<>();
        position.add(this.coordinate);
        return position;
    }
}
