package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    private final double height;

    /**
     * constructor
     * @param axisRay
     * @param radius
     * @param height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return super.toString() +
                "height=" + height;
    }
}