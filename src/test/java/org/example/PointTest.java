package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PointTest {
    private final Movable defaultCenterPoint = new Point(0, 0);

    @Test
    @DisplayName("Point has correct position")
    void testPointHasCorrectPosition() {
        assertArrayEquals(new double[]{0, 0}, this.defaultCenterPoint.getPosition());
    }

    @Test
    @DisplayName("Point moves")
    void testPointMoves() {
        Point point = new Point(1, 1);
        point.move(0, 0);
        assertArrayEquals(new double[]{0, 0}, point.getPosition());
    }
}
