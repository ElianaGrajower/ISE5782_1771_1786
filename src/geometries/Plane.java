package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    private Point q0;
    private Vector normal;

    public Plane(Point vertex1, Point vertex2, Point vertex3) {
        normal = null;
        q0 = vertex1;
    }

    public Plane(Point point, Vector vector){
        q0 = point;
        normal = vector.normalize();
    }

    public Point getQ0() {
        return q0;
    }

    @Override
    public String toString() {
        return  "q0=" + q0 +
                ", normal=" + normal;
    }

    public Vector getNormal(){
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
