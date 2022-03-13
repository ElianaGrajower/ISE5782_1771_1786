package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
    public  Vector getNormal(Point point) {


        Point P0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        Vector P0_P = point.subtract(P0);

        double t = alignZero(v.dotProduct(P0_P));

        if (isZero(t)) {
            return P0_P.normalize();
        }

        Point o = P0.add(v.scale(t));

        if (point.equals(o)) {
            throw new IllegalArgumentException("point cannot be on the tube axis");
        }

        Vector n = point.subtract(o).normalize();

        return n;
    }


}

