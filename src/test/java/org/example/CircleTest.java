package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CircleTest {
    @Test
    @DisplayName("Rectangle draws correctly")
    void testCircleDraws() {
        Circle circle = new Circle(3);
        ArrayList<int[]> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new int[]{0, 0});

        ArrayList<int[]> actualCoordinates = circle.getCoordinates();

        assertArrayEquals(expectedCoordinates.toArray(), actualCoordinates.toArray());
    }

    @Test
    @DisplayName("Rectangle draws correctly")
    void testCircleMovesLeft() {
        Circle circle = new Circle(3);
        ArrayList<int[]> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new int[]{-1, 0});

        circle.moveLeft(1);
        ArrayList<int[]> actualCoordinates = circle.getCoordinates();

        assertArrayEquals(expectedCoordinates.toArray(), actualCoordinates.toArray());
    }

}