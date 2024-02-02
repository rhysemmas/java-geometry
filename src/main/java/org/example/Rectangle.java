package org.example;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Point {
    private final int length;
    private final int width;


    public Rectangle(int bottomLeftX, int bottomLeftY, int length, int width) {
        this.coordinate = new double[]{bottomLeftX, bottomLeftY};
        this.length = length;
        this.width = width;
    }

    @Override
    public List<double[]> draw() {
        return this.calculateCorners();
    }

    private List<double[]> calculateCorners() {
        double[] bottomRightCorner = new double[]{this.coordinate[0] + length, this.coordinate[1]};
        double[] topLeftCorner = new double[]{this.coordinate[0], this.coordinate[1] + width};
        double[] topRightCorner = new double[]{this.coordinate[0] + length, this.coordinate[1] + width};

        ArrayList<double[]> corners = new ArrayList<>();
        corners.add(this.coordinate);
        corners.add(bottomRightCorner);
        corners.add(topLeftCorner);
        corners.add(topRightCorner);

        return corners;
    }
}
