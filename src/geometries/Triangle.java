package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * a class of triangles
 * @author Mikhal Levi and Eliana Grajower
 */
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
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane+
                '}';

    }

    /**
     *
     * @param ray ray
     * @param maxDistance the max distance that it can have
     * @return
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance)
    {
        List<GeoPoint> result = plane.findGeoIntersections(ray);
        if(result == null) {return null;}
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);

        Vector v1 = p1.subtract(p0);// p0->p1
        Vector v2 = p2.subtract(p0);// p0->p2
        Vector v3 = p3.subtract(p0);//p0->p3

        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);

        double s1 = n1.dotProduct(v);
        double s2 = n2.dotProduct(v);
        double s3 = n3.dotProduct(v);

        if(s1> 0 && s2 > 0 && s3 > 0 ||  s1 < 0 && s2< 0 && s3 < 0)
            return List.of(new GeoPoint(this,result.get(0).point));
        return null;
    }
}
