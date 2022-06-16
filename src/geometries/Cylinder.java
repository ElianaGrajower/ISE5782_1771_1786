package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 *  a class of cylinders
 * @author Mikhal Levi and Eliana Grajower
 */
public class Cylinder extends Tube {
    final private double height;

    /**
     * create a cylinder with a ray and a radius
     * @param exisRay the ray on which the cylinder is based
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(Ray exisRay, double radius, double height) {
        super(exisRay, radius);
        this.height = height;
    }

    /**
     *method to find a normal in a point
     * @param point points
     * @return vector
     */
    @Override
    public Vector getNormal(Point point) {
        double t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
        if (isZero(t) || isZero(t - height))
            return axisRay.getDir();
        else
            return super.getNormal(point);
    }

    /**
     * find intersections and shape that intersect the ray
     * @param ray a ray
     * @param maxDistance the distances max
     * @return a list of geo points
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> res = new ArrayList<>();
        List<GeoPoint> lst = super.findGeoIntersectionsHelper(ray, maxDistance);
        if (lst != null)
            for (GeoPoint geoPoint : lst) {
                double distance = alignZero(geoPoint.point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()));
                if (distance > 0 && distance <= height)
                    res.add(geoPoint);
            }

        if (res.size() == 0)
            return null;
        return res;
    }


}
