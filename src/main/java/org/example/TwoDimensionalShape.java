package org.example;

import java.util.ArrayList;

abstract class TwoDimensionalShape {
    public int[] startingCoordinate;

    public abstract ArrayList<int[]> draw();

    public void moveLeft(int offset) {
        startingCoordinate[0] -= offset;
    }

    public void moveRight(int offset) {
        startingCoordinate[0] += offset;
    }

    public void moveUp(int offset) {
        startingCoordinate[1] += offset;
    }

    public void moveDown(int offset) {
        startingCoordinate[1] -= offset;
    }
}


