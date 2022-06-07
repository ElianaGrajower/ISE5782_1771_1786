package geometries;

import primitives.*;

import java.util.*;
import java.util.List;


/**
 * class for geometries
 * @author Mikhal Levy & Eliana Grajower
 */
public class Geometries extends Intersectable{
    private List<Intersectable> intersectables = new LinkedList<>();

    /**
     * constructor
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * constructor
     * @param intersectables
     */
    public Geometries(List<Intersectable> intersectables) {
        this.intersectables = new LinkedList<>();
    }

    /**
     * adds all the geometries to the intersectable collection
     * @param geometries
     */
    public void add(Intersectable... geometries) {

        Collections.addAll(intersectables, geometries);
    }


    /**
     *
     * @param ray
     * @param maxDistance
     * @return
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


