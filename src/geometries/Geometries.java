package geometries;

import primitives.*;

import java.util.*;
import java.util.List;


/**
 * class for geometries
 * @author Mikhal Levy and Eliana Grajower
 */
public class Geometries extends Intersectable{
    private List<Intersectable> intersectables = new LinkedList<>();

    /**
     * constructor
     * @param geometries geometric shapes
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * constructor
     * @param intersectables things that intersect
     */
    public Geometries(List<Intersectable> intersectables) {
        this.intersectables = new LinkedList<>();
    }

    /**
     * adds all the geometries to the intersectable collection
     * @param geometries geometric shapes
     */
    public void add(Intersectable... geometries) {

        Collections.addAll(intersectables, geometries);
    }


    /**
     *find intersections between the ray and geometries
     * @param ray a ray
     * @param maxDistance the distances max
     * @return a list of geo points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        {
            List<GeoPoint> intersections = null;
            for (Intersectable geometry : intersectables) {
                var geoIntersections = geometry.findGeoIntersections(ray);
                if (geoIntersections != null) {
                    if (intersections == null) {
                        intersections = new LinkedList<>();
                    }
                    intersections.addAll(geoIntersections);
                }
            }
            return intersections;

}
    }

}


