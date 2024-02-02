package org.example;

import java.util.List;

public interface Movable {
    void move(double x, double y);

    double[] getPoint();

    List<double[]> draw();
}
