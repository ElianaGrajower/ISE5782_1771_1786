package renderer;

import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * @author Mikhal Levy & Eliana Grajower
 */
public class RayTracerBasic extends RayTracerBase{
    /**
     * constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    private Color calcColor(GeoPoint geoPoint)
    {
        return scene.ambientLight.getIntensity(); //.add(geoPoint.geometry.getEmission());
    }
    /**
     * abstract method that receives a ray.
     * @param ray
     * @return a color
     */
    @Override
    public Color traceRay(Ray ray) {
        Geometries geometries = scene.geometries;
        List<GeoPoint> intersection = geometries.findGeoIntersections(ray);
        if (intersection == null)
        {
            return scene.background;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersection);
        return calcColor(closestPoint);
    }
}


