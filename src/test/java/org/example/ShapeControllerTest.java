package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ShapeControllerTest {
    private Movable defaultCenterPoint = new Point(0, 0);

    @Test
    @DisplayName("Circle with positive coordinate in group still has correct coordinate after noop move")
    void testGroupMoveWithoutMovingCenter() {
        Circle c1 = new Circle(new Point(2, 2), 1);
        Circle c2 = new Circle(this.defaultCenterPoint, 1);

        ShapeController controller = new ShapeController();

        controller.addShape("c1", c1);
        controller.addShape("c2", c2);

        String[] shapes = new String[]{"c1", "c2"};

        controller.createGroup("group1", shapes);
        controller.moveGroup("group1", 1, 1);

        Map<String, Shape> group = controller.getGroup("group1");

        double[] expectedC1CCoordinate = new double[]{2, 2};
        Shape actualC1 = group.get("c1");

        assertArrayEquals(expectedC1CCoordinate, actualC1.getCenter().getPosition());
    }

    @Test
    @DisplayName("Circle with negative coordinate in group still has correct coordinate after noop move")
    void testGroupMoveNegativeWithoutMovingCenter() {
        Circle c1 = new Circle(new Point(-2, -2), 1);
        Circle c2 = new Circle(this.defaultCenterPoint, 1);

        ShapeController controller = new ShapeController();

        controller.addShape("c1", c1);
        controller.addShape("c2", c2);

        String[] shapes = new String[]{"c1", "c2"};

        controller.createGroup("group1", shapes);
        controller.moveGroup("group1", -1, -1);

        Map<String, Shape> group = controller.getGroup("group1");

        double[] expectedC1CCoordinate = new double[]{-2, -2};
        Shape actualC1 = group.get("c1");

        assertArrayEquals(expectedC1CCoordinate, actualC1.getCenter().getPosition());
    }

    @Test
    @DisplayName("Circle with positive coordinate in group has correct coordinate after move")
    void testGroupMove() {
        Circle c1 = new Circle(new Point(10, 10), 1);
        Circle c2 = new Circle(this.defaultCenterPoint, 1);

        ShapeController controller = new ShapeController();

        controller.addShape("c1", c1);
        controller.addShape("c2", c2);

        String[] shapes = new String[]{"c1", "c2"};

        controller.createGroup("group1", shapes);
        controller.moveGroup("group1", 10, 10);

        Map<String, Shape> group = controller.getGroup("group1");

        double[] expectedC1CCoordinate = new double[]{15, 15};
        Shape actualC1 = group.get("c1");

        assertArrayEquals(expectedC1CCoordinate, actualC1.getCenter().getPosition());
    }

    @Test
    @DisplayName("Circle with negative coordinate in group has correct coordinate after move")
    void testGroupMoveNegative() {
        Circle c1 = new Circle(new Point(-10, -10), 1);
        Circle c2 = new Circle(this.defaultCenterPoint, 1);

        ShapeController controller = new ShapeController();

        controller.addShape("c1", c1);
        controller.addShape("c2", c2);

        String[] shapes = new String[]{"c1", "c2"};

        controller.createGroup("group1", shapes);
        controller.moveGroup("group1", -10, -10);

        Map<String, Shape> group = controller.getGroup("group1");

        double[] expectedC1CCoordinate = new double[]{-15, -15};
        Shape actualC1 = group.get("c1");

        assertArrayEquals(expectedC1CCoordinate, actualC1.getCenter().getPosition());
    }

    @Test
    @DisplayName("Circles' coordinates are correct after moving in the negative direction")
    void testGroupMovePositiveAndNegativeMovingNegative() {
        Circle c1 = new Circle(new Point(-10, -10), 1);
        Circle c2 = new Circle(new Point(10, 10), 1);

        ShapeController controller = new ShapeController();

        controller.addShape("c1", c1);
        controller.addShape("c2", c2);

        String[] shapes = new String[]{"c1", "c2"};

        controller.createGroup("group1", shapes);
        controller.moveGroup("group1", -10, -10);

        Map<String, Shape> group = controller.getGroup("group1");

        double[] expectedC1CCoordinate = new double[]{-20, -20};
        Shape actualC1 = group.get("c1");

        double[] expectedC2CCoordinate = new double[]{0, 0};
        Shape actualC2 = group.get("c2");

        assertArrayEquals(expectedC1CCoordinate, actualC1.getCenter().getPosition());
        assertArrayEquals(expectedC2CCoordinate, actualC2.getCenter().getPosition());
    }

    @Test
    @DisplayName("Circles' coordinates are correct after moving group in the positive direction")
    void testGroupMovePositiveAndNegativeMovingPositive() {
        Circle c1 = new Circle(new Point(-10, -10), 1);
        Circle c2 = new Circle(new Point(10, 10), 1);

        ShapeController controller = new ShapeController();

        controller.addShape("c1", c1);
        controller.addShape("c2", c2);

        String[] shapes = new String[]{"c1", "c2"};

        controller.createGroup("group1", shapes);
        controller.moveGroup("group1", 10, 10);

        Map<String, Shape> group = controller.getGroup("group1");

        double[] expectedC1CCoordinate = new double[]{0, 0};
        Shape actualC1 = group.get("c1");

        double[] expectedC2CCoordinate = new double[]{20, 20};
        Shape actualC2 = group.get("c2");

        assertArrayEquals(expectedC1CCoordinate, actualC1.getCenter().getPosition());
        assertArrayEquals(expectedC2CCoordinate, actualC2.getCenter().getPosition());
    }

    @Test
    @DisplayName("Circles' coordinates are correct after moving group with positive X and negative Y")
    void testGroupMovePositiveAndNegativeMovingPositiveXNegativeY() {
        Circle c1 = new Circle(new Point(-10, -10), 1);
        Circle c2 = new Circle(new Point(10, 10), 1);

        ShapeController controller = new ShapeController();

        controller.addShape("c1", c1);
        controller.addShape("c2", c2);

        String[] shapes = new String[]{"c1", "c2"};

        controller.createGroup("group1", shapes);
        controller.moveGroup("group1", -10, 10);

        Map<String, Shape> group = controller.getGroup("group1");

        double[] expectedC1CCoordinate = new double[]{-20, 0};
        Shape actualC1 = group.get("c1");

        double[] expectedC2CCoordinate = new double[]{0, 20};
        Shape actualC2 = group.get("c2");

        assertArrayEquals(expectedC1CCoordinate, actualC1.getCenter().getPosition());
        assertArrayEquals(expectedC2CCoordinate, actualC2.getCenter().getPosition());
    }
}
