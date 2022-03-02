package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{
    protected final Ray axisRay;
    protected final double radius;

    /**
     * constructor
     * @param axisRay
     * @param radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * prints the tube type
     * @return
     */
    @Override
    public String toString() {
        return "axisRay=" + axisRay +
                ", radius=" + radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
