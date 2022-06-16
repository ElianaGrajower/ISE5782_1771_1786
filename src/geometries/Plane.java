package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * a class of planes
 * @author Mikhal Levi and Eliana Grajower
 */
public class Plane extends FlatGeometry{
    private final Point q0;
    private final Vector normal;


    /**
     * constructor for plane
     * calculates normal according to equation
     * @param vertex1
     * @param vertex2
     * @param vertex3
     */
    public Plane(Point vertex1, Point vertex2,Point vertex3) {
        if(vertex1.equals(vertex2)||vertex2.equals(vertex3)||vertex1.equals(vertex3))
            throw new IllegalArgumentException("2 of the points are the same");
       Vector u1=vertex2.subtract(vertex1);
        Vector u2=vertex3.subtract(vertex1);
        if(u1.normalize().dotProduct(u2.normalize())==1)
            throw new IllegalArgumentException("the points are on the same line");
        this.q0 = vertex1;
        normal= (u1.crossProduct(u2)).normalize();
    }
    /**
     * constructor
     * @param point
     * @param vector
     */
    public Plane(Point point, Vector vector){
        q0 = point;
        normal = vector.normalize();
    }

    public Point getQ0() {
        return q0;
    }

    /**
     * prints the objects of plane type
     * @return
     */
    @Override
    public String toString() {
        return  "q0=" + q0 +
                ", normal=" + normal;
    }

    public Vector getNormal(){
      return normal;
    }

    /**
     * method to find a normal in a point
     * @param point
     * @return
     */
    @Override
    public  Vector getNormal(Point point) {

        return normal;
    }



    /**
     * a function that helps the findGeoIntersections function
     * @param ray
     * @return a list of geo points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance)
    {
        List<Point> points = null;

        Point P0 = ray.getP0();
        Vector v = ray.getDir();

        Vector n = normal;

        if(this.q0.equals(P0))
        {
            return  null;
        }

        Vector P0_Q0 = this.q0.subtract(P0);

        //numerator
        double nP0Q0  = alignZero(n.dotProduct(P0_Q0));

        //
        if (isZero(nP0Q0 )){
            return null;
        }

        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if(isZero(nv)){
            return null;
        }

        double  t = alignZero(nP0Q0  / nv);

        if (t < 0 || alignZero(t - maxDistance) > 0){
            return null;
        }

        Point point = ray.getPoint(t);

        points = List.of(point);


        if (points == null) return null;
        return points.stream().map(p -> new GeoPoint(this, p)).toList();
    }
}
