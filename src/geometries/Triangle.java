package geometries;

import primitives.Point;

public class Triangle extends Polygon{
    /**
     * constructor
     * @param point1
     * @param point2
     * @param point3
     */
    public Triangle(Point point1, Point point2, Point point3) {
        super(point1, point2, point3);
    }

    @Override
    public String toString() {
        return  "vertices=" + vertices +
                ", plane=" + plane;
    }

}
