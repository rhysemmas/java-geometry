package org.example;

public interface Shape {
    Movable getCenter();

    Movable[] getCorners() throws HasNoCornersException;
}
