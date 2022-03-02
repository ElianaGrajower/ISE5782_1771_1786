package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{
    private final Point center;
    private final double radius;

    /**
     * constructor
     * @param center
     * @param radius
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * prints the sphere type
     * @return
     */
    @Override
    public String toString() {
        return "center=" + center +
                ", radius=" + radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
