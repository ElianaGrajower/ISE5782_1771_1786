package renderer;

import geometries.Geometries;
import geometries.Geometry;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;


/**
 * @author Mikhal Levy & Eliana Grajower
 */
public class RayTracerBasic extends RayTracerBase{

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K =Double3.ONE;
    /**
     * size of mooving the rays head for shadow rays
     */
    private static final double DELTA = 0.1;
    private static final double EPS = 0.1;//help value
    /**
     * constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    private Color calcColor(GeoPoint geoPoint, Ray ray)
    {
        Color result =  scene.ambientLight.getIntensity();
        result = result.add(calcLocalEffects(geoPoint, ray));
        return  result;
    }

    /**
     *
     * @param gp
     * @param light
     * @param l
     * @param n
     * @return
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);//from point to light source
        // Refactored ray head move
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        double maxDistance = light.getDistance(gp.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

        //if there are no intersections return true (there is no shadow)
        if (intersections == null)
            return Double3.ONE;
        intersections.removeIf( (geoPoint) ->
        {
            double dist = geoPoint.point.distance(gp.point);
            return (dist >= maxDistance);
        });

//        for (var geo : intersections) {
//            double dist = geo.point.distance(gp.point);
//            //if the point is farther than the maximum distance- remove from list.
//            if (dist >= maxDistance)
//                intersections.remove(geo);
//
//        }

        if (intersections.isEmpty())
            return Double3.ONE;


        Double3 ktr = Double3.ONE;
        //for each intersection
        for (var intersection : intersections) {
            ktr = intersection.geometry.getMaterial().kT.product(ktr);
            if (!ktr.lowerThan(MIN_CALC_COLOR_K))
                return Double3.ZERO;
        }
        return ktr;
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
        return calcColor(closestPoint,ray);
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Color color = intersection.geometry.getEmission();
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = intersection.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)

                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(
                        lightIntensity.scale(calcDiffusive(material, nl)),
                        lightIntensity.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n)*2)); //nl must not be zero
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Double3.ZERO; //view from direction opposite to r vector
        return material.kS.scale(Math.pow(minusVR, material.getnShininess()));
    }

    private Double3 calcDiffusive(Material material, double nl) {
        nl = Math.abs(nl);
        return  material.kD.scale(nl);
    }

    /**
     * verification of unshaded between a point and a light source
     * @param gp
     * @param lightSource
     * @param l
     * @param n
     * @param nv
     * @return
     */


}



