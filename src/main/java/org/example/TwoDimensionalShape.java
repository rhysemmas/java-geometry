package org.example;

public class TwoDimensionalShape {
    public int[] startingCoordinates;

    public void draw() {
        System.out.println(this.startingCoordinates[0] + ", " + this.startingCoordinates[1]);
    }

    public void moveLeft(int offset) {
        this.startingCoordinates[0] -= offset;
    }

    public void moveRight(int offset) {
        this.startingCoordinates[0] += offset;
    }

    public void moveUp(int offset) {
        this.startingCoordinates[1] += offset;
    }

    public void moveDown(int offset) {
        this.startingCoordinates[1] -= offset;
    }
}


