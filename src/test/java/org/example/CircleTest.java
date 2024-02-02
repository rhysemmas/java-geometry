package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CircleTest {
    @Test
    @DisplayName("Circle draws correctly")
    void testCircleDraws() {
        Circle circle = new Circle(0, 0, 1);
        ArrayList<double[]> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new double[]{0, 0});

        assertArrayEquals(expectedCoordinates.toArray(), circle.draw().toArray());
    }
}