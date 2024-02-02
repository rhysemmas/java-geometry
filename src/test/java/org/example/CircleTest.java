package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CircleTest {
    @Test
    @DisplayName("Circle draws correctly")
    void testCircleMovesLeft() {
        Circle circle = new Circle(3);
        int[] expectedCoordinates = new int[]{-1, 0};

        circle.moveLeft(1);

        assertArrayEquals(expectedCoordinates, circle.startingCoordinates);
    }
}