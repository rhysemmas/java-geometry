package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CircleTest {
    private final Movable defaultCenterPoint = new Point(0, 0);

    @Test
    @DisplayName("Circle has correct center point")
    void testCircleHasCorrectCenterPoint() {
        Circle circle = new Circle(this.defaultCenterPoint, 1);

        assertEquals(this.defaultCenterPoint, circle.getCenter());
    }

    @Test
    @DisplayName("Circle throws no corners exception")
    void testCircleThrowsNoCorners() {
        Circle circle = new Circle(this.defaultCenterPoint, 1);

        assertThrows(HasNoCornersException.class, circle::getCorners);
    }
}