package org.example;

// TODO: linked list of two dimensional shapes?
public class GroupedShape extends TwoDimensionalShape {
    private final TwoDimensionalShape[] shapes;

    public GroupedShape(TwoDimensionalShape[] shapes) {
        this.shapes = shapes;
    }

    public void moveLeft(int offset) {
        for (TwoDimensionalShape shape : this.shapes) {
            shape.moveLeft(offset);
        }
    }

    public void moveRight(int offset) {
        for (TwoDimensionalShape shape : this.shapes) {
            shape.moveRight(offset);
        }
    }

    public void moveUp(int offset) {
        for (TwoDimensionalShape shape : this.shapes) {
            shape.moveUp(offset);
        }
    }

    public void moveDown(int offset) {
        for (TwoDimensionalShape shape : this.shapes) {
            shape.moveDown(offset);
        }
    }

    public void draw() {
        int i = 1;
        for (TwoDimensionalShape shape : this.shapes) {
            System.out.println("Group member '" + i + "' coordinates:");
            shape.draw();
            i++;
        }
    }
}
