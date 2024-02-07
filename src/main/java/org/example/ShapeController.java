package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShapeController {
    private Map<String, Shape> shapes;
    private Map<String, Map<String, Shape>> groups;

    public ShapeController() {
        this.shapes = new HashMap<>();
        this.groups = new HashMap<>();
    }

    public Map<String, Shape> listShapes() {
        return this.shapes;
    }

    public void addShape(String name, Shape shape) {
        shapes.put(name, shape);
    }

    public Shape getShape(String name) throws IllegalArgumentException {
        Shape shape = this.shapes.get(name);
        if (shape == null) {
            throw new IllegalArgumentException("Shape '" + name + "' does not exist!");
        }

        return shape;
    }

    /* TODO: could consider if user tries to move a shape which is part of a group, the entire group moves
        (rather than them having to address the group directly) */
    public void moveShape(String name, int x, int y) throws IllegalArgumentException {
        try {
            Shape shape = this.getShape(name);
            Movable center = shape.getCenter();
            center.move(x, y);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public List<double[]> getCoordinates(Shape shape) {
        List<double[]> coordinates = new ArrayList<>();
        try {
            Movable[] corners = shape.getCorners();
            for (Movable corner : corners) {
                coordinates.add(corner.getPosition());
            }
        } catch (HasNoCornersException hnce) {
            Movable center = shape.getCenter();
            coordinates.add(center.getPosition());
        }

        return coordinates;
    }

    public Map<String, Map<String, Shape>> listGroups() {
        return this.groups;
    }

    public void createGroup(String groupName, String[] shapesToGroup) throws IllegalArgumentException {
        Map<String, Shape> groupedShapes = new HashMap<>();

        for (String shapeName : shapesToGroup) {
            try {
                Shape shape = this.getShape(shapeName);
                groupedShapes.put(shapeName, shape);

            } catch (IllegalArgumentException exception) {
                throw new IllegalArgumentException(exception);
            }
        }

        this.groups.put(groupName, groupedShapes);
    }

    public Map<String, Shape> getGroup(String name) throws IllegalArgumentException {
        try {
            return this.groups.get(name);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public void moveGroup(String name, int x, int y) throws IllegalArgumentException {
        try {
            Map<String, Shape> group = this.getGroup(name);
            double[] centerOfGroup = this.calculateCenterOfGroup(group);
            double[] offSet = this.calculateOffset(centerOfGroup, new double[]{x, y});

            for (Shape shape : group.values()) {
                Movable centerPoint = shape.getCenter();
                double[] coordinate = centerPoint.getPosition();
                double newX = coordinate[0] + offSet[0];
                double newY = coordinate[1] + offSet[1];

                centerPoint.move(newX, newY);
            }

        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    private double[] calculateCenterOfGroup(Map<String, Shape> shapes) {
        double xCoordinates = 0, yCoordinates = 0;

        int i = 0;
        for (String shapeName : shapes.keySet()) {
            double[] coordinate = shapes.get(shapeName).getCenter().getPosition();
            xCoordinates += coordinate[0];
            yCoordinates += coordinate[1];
            i++;
        }

        double centerX = xCoordinates / i;
        double centerY = yCoordinates / i;

        return new double[]{centerX, centerY};
    }

    private double[] calculateOffset(double[] oldCoordinate, double[] newCoordinate) {
        double offsetX = newCoordinate[0] - oldCoordinate[0];
        double offsetY = newCoordinate[1] - oldCoordinate[1];

        return new double[]{offsetX, offsetY};
    }
}
