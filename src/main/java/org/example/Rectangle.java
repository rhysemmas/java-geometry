package org.example;

public class Rectangle implements Shape {
    private final int length;
    private final int width;
    private final Movable centerPoint;

    public Rectangle(Movable centerPoint, int length, int width) {
        this.length = length;
        this.width = width;
        this.centerPoint = centerPoint;
    }

    public Movable[] getCorners() {
        return this.calculateCorners();
    }

    public Movable getCenter() {
        return this.centerPoint;
    }

    private Movable[] calculateCorners() {
        double[] centerCoordinate = this.centerPoint.getPosition();
        Movable bottomLeftCorner = new Point((centerCoordinate[0] - ((double) this.length / 2)), (centerCoordinate[1] - ((double) this.width / 2)));
        Movable bottomRightCorner = new Point((centerCoordinate[0] + ((double) this.length / 2)), (centerCoordinate[1] - ((double) this.width / 2)));
        Movable topLeftCorner = new Point((centerCoordinate[0] - ((double) this.length / 2)), (centerCoordinate[1] + ((double) this.width / 2)));
        Movable topRightCorner = new Point((centerCoordinate[0] + ((double) this.length / 2)), (centerCoordinate[1] + ((double) this.width / 2)));

        return new Movable[]{bottomLeftCorner, bottomRightCorner, topLeftCorner, topRightCorner};
    }
}
