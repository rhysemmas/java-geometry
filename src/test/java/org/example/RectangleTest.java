package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleTest {
    private final Movable defaultCenterPoint = new Point(0, 0);

    @Test
    @DisplayName("Rectangle has correct center point")
    void testRectangleHasCorrectCenter() {
        Rectangle rectangle = new Rectangle(this.defaultCenterPoint, 2, 1);

        assertEquals(this.defaultCenterPoint, rectangle.getCenter());
    }

    @Test
    @DisplayName("Rectangle has correct corners")
    void testRectangleHasCorrectCorners() {
        Rectangle rectangle = new Rectangle(this.defaultCenterPoint, 10, 6);

        Movable bottomLeftCorner = new Point(-5, -3);
        Movable bottomRightCorner = new Point(5, -3);
        Movable topLeftCorner = new Point(-5, 3);
        Movable topRightCorner = new Point(5, 3);

        ArrayList<double[]> expectedCorners = new ArrayList<>();
        expectedCorners.add(bottomLeftCorner.getPosition());
        expectedCorners.add(bottomRightCorner.getPosition());
        expectedCorners.add(topLeftCorner.getPosition());
        expectedCorners.add(topRightCorner.getPosition());

        ArrayList<double[]> actualCorners = new ArrayList<>();
        Movable[] rectangleCorners = rectangle.getCorners();
        for (Movable corner : rectangleCorners) {
            actualCorners.add(corner.getPosition());
        }

        assertArrayEquals(expectedCorners.toArray(), actualCorners.toArray());
    }
}
