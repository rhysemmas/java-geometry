package org.example;

import java.util.HashMap;
import java.util.Map;

public class ShapeController {
    private Map<String, Movable> shapes;
    private Map<String, Map<String, Movable>> groups;

    public ShapeController() {
        this.shapes = new HashMap<>();
        this.groups = new HashMap<>();
    }

    public Map<String, Movable> listShapes() {
        return this.shapes;
    }

    public void addShape(String name, Movable shape) {
        shapes.put(name, shape);
    }

    public Movable getShape(String name) throws IllegalArgumentException {
        Movable shape = this.shapes.get(name);
        if (shape == null) {
            throw new IllegalArgumentException("Shape '" + name + "' does not exist!");
        }

        return shape;
    }

    /* TODO: could consider if user tries to move a shape which is part of a group, the entire group moves
        (rather than them having to address the group directly) */
    public void moveShape(String name, int x, int y) throws IllegalArgumentException {
        try {
            Movable shape = this.getShape(name);
            shape.move(x, y);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public Map<String, Map<String, Movable>> listGroups() {
        return this.groups;
    }

    public void createGroup(String groupName, String[] shapesToGroup) throws IllegalArgumentException {
        Map<String, Movable> groupedShapes = new HashMap<>();

        for (String shapeName : shapesToGroup) {
            try {
                Movable shape = getShape(shapeName);
                groupedShapes.put(shapeName, shape);

            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException(exception);
            }
        }

        this.groups.put(groupName, groupedShapes);
    }

    public Map<String, Movable> getGroup(String name) throws IllegalArgumentException {
        try {
            return this.groups.get(name);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public void moveGroup(String name, int x, int y) throws IllegalArgumentException {
        try {
            Map<String, Movable> group = this.getGroup(name);
            double[] centerOfGroup = this.calculateCenterOfGroup(group);
            double[] offSet = this.calculateOffset(centerOfGroup, new double[]{x, y});

            for (Movable shape : group.values()) {
                double[] coordinate = shape.getPoint();
                double newX = coordinate[0] + offSet[0];
                double newY = coordinate[1] + offSet[1];

                shape.move(newX, newY);
            }

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    private double[] calculateCenterOfGroup(Map<String, Movable> shapes) {
        double xCoordinates = 0, yCoordinates = 0;

        int i = 0;
        for (String shapeName : shapes.keySet()) {
            double[] coordinate = shapes.get(shapeName).getPoint();
            xCoordinates += coordinate[0];
            yCoordinates += coordinate[1];
            i++;
        }

        double centerX = xCoordinates / i;
        double centerY = yCoordinates / i;

        return new double[]{centerX, centerY};
    }

    private double[] calculateOffset(double[] oldCoordinate, double[] newCoordinate) {
        double offsetX = 0, offsetY = 0;

        offsetX = newCoordinate[0] - oldCoordinate[0];
        offsetY = newCoordinate[1] - oldCoordinate[1];

        return new double[]{offsetX, offsetY};
    }
}
