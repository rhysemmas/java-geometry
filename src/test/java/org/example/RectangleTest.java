package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RectangleTest {
    @Test
    @DisplayName("Rectangle draws correctly")
    void testRectangleDraws() {
        Rectangle rectangle = new Rectangle(5, 3);
        ArrayList<int[]> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new int[]{0, 0});
        expectedCoordinates.add(new int[]{5, 0});
        expectedCoordinates.add(new int[]{0, 3});
        expectedCoordinates.add(new int[]{5, 3});

        ArrayList<int[]> actualCoordinates = rectangle.draw();

        assertArrayEquals(expectedCoordinates.toArray(), actualCoordinates.toArray());
    }

    @Test
    @DisplayName("Rectangle moves left correctly")
    void testRectangleMovesLeft() {
        Rectangle rectangle = new Rectangle(5, 3);
        ArrayList<int[]> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new int[]{-1, 0});
        expectedCoordinates.add(new int[]{4, 0});
        expectedCoordinates.add(new int[]{-1, 3});
        expectedCoordinates.add(new int[]{4, 3});

        rectangle.moveLeft(1);
        ArrayList<int[]> actualCoordinates = rectangle.draw();

        assertArrayEquals(expectedCoordinates.toArray(), actualCoordinates.toArray());
    }
}
