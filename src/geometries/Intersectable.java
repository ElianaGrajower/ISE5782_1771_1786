package geometries;

import primitives.*;

import java.util.List;

/**
 * interface for finding intersection points
 * @author Mikhal Levy & Eliana Grajower
 */
public interface Intersectable {
    /**
     *
     * @param ray {@link Ray} pointing towards the object
     * @return List of intersection {@link Point}s
     */
    public List<Point> findIntersections(Ray ray);
}
