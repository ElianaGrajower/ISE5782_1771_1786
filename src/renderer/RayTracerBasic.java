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
    private static final Double3 INITIAL_K =new Double3(1);
    /**
     * constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }
    private Color calcColor(GeoPoint geoPoint, Ray ray)
    {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }
    private Color calcColor(GeoPoint geoPoint,Ray ray,Double3 k)
    {
              return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission())
                .add(calcLocalEffects(geoPoint, ray));
    }

    /**
     *
     * @param gp
     * @param ray
     * @param k
     * @return
     *//**
    private Color calcLocalEffects1(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return Color.BLACK;
        int nShininess = gp.geometry.getMaterial().getnShininess();
        Double3 kd = gp.geometry.getMaterial().kD;
        Double3 ks = gp.geometry.getMaterial().kS;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0)
            { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp,lightSource,l, n);
                if(ktr.product(k).biggerThan(MIN_CALC_COLOR_K))
                {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));

                }
            }
        }
        return color;
    }*/

    /**
     *
     * @param material
     * @param nl
     * @return
     */
/**
    private Double3 calcDiffusive(Material material, Double3 nl) {
        return material.kD.scale(Math.abs(nl));
    }*/

    /**
     *
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return
     *//**
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
        double minusVR = 0 - alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Double3.ZERO;
        return material.kS.scale(Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), material.getnShininess()));
    }*/

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
            return new Double3(1.0);

        for (var geo : intersections) {
            double dist = geo.point.distance(gp.point);
            //if the point is farther than the maximum distance- remove from list.
            if (dist >= maxDistance)
                intersections.remove(geo);

        }

        if (intersections.isEmpty())
            return new Double3(1.0);


        Double3 ktr = new Double3(1.0);
        //for each intersection
        for (GeoPoint intersection : intersections) {
            ktr = intersection.geometry.getMaterial().kT.product(ktr);
            if (ktr.lowerThan(MIN_CALC_COLOR_K))
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
            if (nl * nv > 0) { // sign(nl) == sing(nv)
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
}



