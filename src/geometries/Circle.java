package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * a class of circles
 * @author Michal Levi and Eliana Grajower
 */
public class Circle extends Geometry {


    final private Point center;
    final private double radius;
    final private Vector normal;
    protected Plane plane;

    /**
     * constructor
     * @param center the center
     * @param radius the radius
     * @param normal the normal
     */
    public Circle(Point center, double radius, Vector normal) {
        this.center = center;
        this.radius = radius;
        this.normal = normal.normalize();
    }


    /**
     * gets the normal
     * @param point a point
     * @return vector
     */
    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * helps to find the intersection
     * @param ray a ray
     * @param maxDistance the max the distance can be
     * @return list of geo points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> planePoints = plane.findGeoIntersectionsHelper(ray, maxDistance);
        if(planePoints == null)
            return  null;

        planePoints.removeIf( //removes from teh list all the point outside the circles range
                (geoPoint) ->
                {return geoPoint.point.distance(center) > radius;}
        );

        if(planePoints.isEmpty())
            return  null;

        return List.of(new GeoPoint(this,planePoints.get(0).point));
    }


}
