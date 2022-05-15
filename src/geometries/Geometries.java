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
     * finds the intersections between the ray and the shapes.
     * @param ray {@link Ray} pointing towards the object
     * @return a list of intersections
     */
  /**  @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (Intersectable item : intersectables) {
            //get intersections points of a particular item from intersectables
            List<Point> itempoints = item.findIntersections(ray);
            if(itempoints!= null){
                //first time initialize result to new LinkedList
                if(result== null){
                    result= new LinkedList<>();
                }
                //add all item points to the resulting list
                result.addAll(itempoints);
            }
        }
        return result;
    }*/

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
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
    }}


