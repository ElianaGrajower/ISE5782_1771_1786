package geometries;

import primitives.*;
import geometries.*;

import java.util.List;

/**
 * interface for finding intersection points
 * @author Mikhal Levy & Eliana Grajower
 */
public abstract class Intersectable {
    /**
     * @param ray {@link Ray} pointing towards the object
     * @return List of intersection {@link Point}s
     */
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    /**
     * a class of geoPoints
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * constructor
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }
        /**
         * checks if objects are equal to each other
         * @param obj
         * @return true or false
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) obj;
            return point.equals(geoPoint.point) && geometry.equals(geoPoint.geometry);
        }
        /**
         * prints a geoPoint type
         * @return
         */
        @Override
        public String toString() {
            return super.toString() + " " + geometry;
        }
    }

}
