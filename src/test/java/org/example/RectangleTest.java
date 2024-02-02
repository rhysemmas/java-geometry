package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RectangleTest {
    @Test
    @DisplayName("Rectangle draws correctly")
    void testRectangleDraws() {
        Rectangle rectangle = new Rectangle(0, 0, 5, 3);
        ArrayList<double[]> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new double[]{0, 0});
        expectedCoordinates.add(new double[]{5, 0});
        expectedCoordinates.add(new double[]{0, 3});
        expectedCoordinates.add(new double[]{5, 3});

        assertArrayEquals(expectedCoordinates.toArray(), rectangle.draw().toArray());
    }

    @Test
    @DisplayName("Rectangle moves left correctly")
    void testRectangleMovesLeft() {
        Rectangle rectangle = new Rectangle(0, 0, 5, 3);
        ArrayList<double[]> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new double[]{-1, 0});
        expectedCoordinates.add(new double[]{4, 0});
        expectedCoordinates.add(new double[]{-1, 3});
        expectedCoordinates.add(new double[]{4, 3});

        rectangle.move(-1, 0);

        assertArrayEquals(expectedCoordinates.toArray(), rectangle.draw().toArray());
    }
}
