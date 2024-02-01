package org.example;

import java.util.ArrayList;

// TODO: linked list of twodimensional shapes rather than having this extra class?
public class GroupedShape {
    private final TwoDimensionalShape[] shapes;

    public GroupedShape(TwoDimensionalShape[] shapes) {
        this.shapes = shapes;
    }

    public ArrayList<ArrayList<int[]>> draw() {
        ArrayList<ArrayList<int[]>> groupedShapes = new ArrayList<>();

        for (TwoDimensionalShape shape : this.shapes) {
            ArrayList<int[]> coordinates = shape.draw();
            groupedShapes.add(coordinates);
        }

        return groupedShapes;
    }
}
