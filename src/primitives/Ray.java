package primitives;

import java.util.List;


import static primitives.Util.alignZero;
import static primitives.Util.isZero;
import geometries.Intersectable.GeoPoint;

/**
 * a class of rays
 * @author Mikhal Levi and Eliana Grajower
 */
public class Ray {

    private static final double DELTA = 0.1;
    private final Point p0;
    private final Vector dir;

    /**
     * constructor
     * @param p0 a point
     * @param dir a direction vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize(); //normalize the vector
    }

    /**
     * constructor for ray a little moved
     * @param point a point
     * @param direction direction vector
     * @param normal normal vector
     */
    public Ray(Point point, Vector normal, Vector direction)
    {
        //point + normal.scale(±EPSILON)
        dir = direction.normalize();

        double nv = alignZero(normal.dotProduct(dir));

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        p0 = point.add(normalDelta);
    }

    /**
     * gets P0
     * @return point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * gets direction
     * @return a vector
     */
    public Vector getDir() {
        return dir;
    }
    /**
     * checks if objects are equal to each other
     * @param obj an object
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray)obj;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * prints a ray type
     * @return a string
     */
    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }

    /**
     * Get point at specific distance in the ray direction
     * @param t distance for reaching new Point
     * @return new {@link Point}
     */
    public Point getPoint(double t)
    {
        if(isZero(t)){
            throw new IllegalArgumentException("t equal 0 cause illegal vector ZERO");
        }
        return p0.add(dir.scale(t));
    }

    /**
     * return the closest point to the ray head
     * @param points a point
     * @return a point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * find the closest geoPoint with the ray
     * @param geoPointList a list of geo points
     * @return geoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList){
       GeoPoint result= null;
       double minDistance = Double.MAX_VALUE;
       double ptDistance;

       for (GeoPoint geoPoint : geoPointList ) {
           ptDistance = geoPoint.point.distanceSquared(p0);
           if( ptDistance < minDistance){
               minDistance = ptDistance;
               result = geoPoint;
           }
       }
       return result;
   }
}

