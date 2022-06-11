package renderer;

import geometries.FlatGeometry;
import geometries.Geometries;
import geometries.Geometry;
import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


/**
 * @author Mikhal Levy & Eliana Grajower
 */
public class RayTracerBasic extends RayTracerBase{

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;
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

    /**
     *
     * @param geopoint
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.getAmbientLight().getIntensity());
    }

    //    private Color calcColor(GeoPoint gp, Ray ray) {
//        Color color = scene.getAmbientLight().getIntensity();
//        color = color.add(calcLocalEffects(gp, ray));
//
//        return color;
//    }

    /**
     *
     * @param geoPoint
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = geoPoint.geometry.getEmission();

        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);

        // check that ray is not parallel to geometry
        double nv = alignZero(n.dotProduct(v));

        if (isZero(nv)) {
            return color;
        }
        Material material = geoPoint.geometry.getMaterial();
        color = color.add(calcLocalEffects(geoPoint, material, n, v, nv, k));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, material,n,v,nv, level, k));
    }

    /**
     *
     * @param gp
     * @param material
     * @param n
     * @param v
     * @param nv
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp,Material material,Vector n, Vector v, double nv, int level, Double3 k) {
        Color color = Color.BLACK;
        Double3 kkr = material.getKr().product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.getKr(), kkr));
        Double3 kkt = material.getKt().product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.getKt(), kkt));
        return color;
    }

    /**
     *
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.getBackground() : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     *
     * @param point
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, n, v);
    }

    /**
     *
     * @param pointGeo
     * @param v
     * @param n
     * @return
     */
    private Ray constructReflectedRay(Point pointGeo, Vector v, Vector n) {
        //r = v - 2.(v.n).n
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, n, r);
    }
    // private Color calcColor(GeoPoint geoPoint, Ray ray)
    //{
    //  Color result =  scene.ambientLight.getIntensity();
    // result = result.add(calcLocalEffects(geoPoint, ray));
    /// return  result;
    // }
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null) {
            return null;
        }
        return ray.findClosestGeoPoint(intersections);
    }
    /**
     * calculates the amount of shadow in the point sometimes we need light shadow
     * and sometimes not
     *
     * @param light - light source
     * @param l     - vector from light
     * @param n     - normal of body
     * @param gp    - point in geometry body
     ** @param nv
     * @return amount of shadow
     */

    protected Double3 transparency (LightSource light, Vector l, Vector n, GeoPoint gp,double nv){
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point,  n,lightDirection);
        double lightDistance = light.getDistance(gp.point);
        var intersections = scene.getGeometries().findGeoIntersections(lightRay);
        Double3 ktr = new Double3(1.0);
        if (intersections == null)
            return ktr;
        for (GeoPoint geopoint : intersections) {
            if(alignZero(geopoint.point.distance(gp.point)-lightDistance)<=0){
                // if (geopoint.point.distance(gp.point) <= lightDistance &&  geopoint.geometry.getMaterial().kT.equals(new Double3(0.0))){
                // var  kt = ktr.product(geopoint.geometry.getMaterial().kT);
                var kt=geopoint.geometry.getMaterial().kT;
                ktr=kt.product(ktr);
                if (ktr.lowerThan(MIN_CALC_COLOR_K))
                    return new Double3(0.0);
            }


        }
        return ktr;

    }
//    /**
//     * The method checks whether there is any object shading the light source from a
//     * point
//     *
//     * @param gp the point with its geometry
//     * @param lightSource light source
//     * @param l  direction from light to the point
//     * @param n normal vector from the surface towards the geometry
//     *
//     * @return accumulated transparency attenuation factor
//     */
//
//    private Double3 transparency(LightSource lightSource, Vector l, Vector n, GeoPoint gp) {
//        // Pay attention to your method of distance screening
//        Vector lightDirection = l.scale(-1); // from point to light source
//        Point point = gp.point;
//        Ray lightRay = new Ray(point, n, lightDirection);
//
//        double maxdistance = lightSource.getDistance(point);
//        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxdistance);
//
//        if (intersections == null)
//            return Double3.ONE;
//
//        Double3 ktr = Double3.ONE;
////        loop over intersections and for each intersection which is closer to the
////        point than the light source multiply ktr by 𝒌𝑻 of its geometry.
////        Performance:
////        if you get close to 0 –it’s time to get out( return 0)
//        for (var geo : intersections) {
//            ktr = ktr.product(geo.geometry.getMaterial().getKt());
//            if (ktr.lowerThan(MIN_CALC_COLOR_K)) {
//                return Double3.ZERO;
//            }
//        }
//        return ktr;
//    }

    /**
     *
     * @param rays
     * @return
     */

    public Color traceRays(List<Ray>rays){
        Color sumColor=Color.BLACK;
        for(Ray ray:rays){
            GeoPoint closestPoint=findClosestIntersection(ray);
            if(closestPoint!=null){
               sumColor=sumColor.add(calcColor(closestPoint,ray));
            }else{
                sumColor=sumColor.add(scene.getBackground(
                ));
            }
        }
        return sumColor.reduce(rays.size());
    }
    /**
     * //add here the lights effects
     *
     * @param gp  geopoint of the intersection
     * @param v ray direction
     * @return resulting color with diffuse and specular
     */
    private Color calcLocalEffects(GeoPoint gp, Material material, Vector n, Vector v, double nv, Double3 k) {
        Color color = Color.BLACK;

        Point point = gp.point;

        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 ktr = transparency(lightSource, l, n, gp, nv);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
//                if (unshaded(gp, lightSource, l, n,nv)) {
                    Color iL = lightSource.getIntensity(point).scale(ktr);
                    color = color.add(
                            calcDiffusive(material.getKd(), nl,iL),
                            calcSpecular(material.getKs(), n, l, nl, v,material.getShininess(),iL));
                }
            }
        }
        return color;
    }

    /**
     *
     * @param kS
     * @param n
     * @param l
     * @param nl
     * @param v
     * @param shininess
     * @param intensity
     * @return
     */
    private Color calcSpecular(Double3 kS, Vector n, Vector l, double nl,Vector v,int shininess,Color intensity) {
        Vector r = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Color.BLACK; // view from direction opposite to r vector
        Double3 amount =kS.scale(Math.pow(minusVR, shininess));
        return intensity.scale(amount);
    }

    /**
     *
     * @param kD
     * @param nl
     * @param intensity
     * @return
     */
    private Color calcDiffusive(Double3 kD, double nl,  Color intensity) {
        double abs_nl = Math.abs(nl);
        Double3 amount =kD.scale(abs_nl);
        return intensity.scale(amount);
    }

    /**
     * The method checks whether there is any object shading the light source from a
     * point
     *
     * @param gp the point with its geometry
     * @param lightSource light source
     * @param l  direction from light to the point
     * @param n normal vector to the surface of gp
     * @param nv dotproduct between n and ray direction
     * @return accumulated transparency attenuation factor
     */

    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nv) {

        Vector lightDirection = l.scale(-1); // from point to light source
        double nl = n.dotProduct(lightDirection);

        Vector delta = n.scale(nl > 0 ? EPS : -EPS);
        Point pointRay = gp.point.add(delta);
        Ray lightRay = new Ray(pointRay, lightDirection);

        double maxdistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, maxdistance);

        if (intersections == null){
            return true;
        }

        for (var item : intersections){
            if (item.geometry.getMaterial().getKt().lowerThan(MIN_CALC_COLOR_K)){
                return false;
            }
        }

        return true;
    }

    /**
     * The method checks whether there is any object shading the light source from a point
     *
     * @param gp the point with its geometry
     * @param ls light source
     * @param l  direction from light to the point
     * @return accumulated transparency attenuation factor
     */
    private boolean unshaded(GeoPoint gp,LightSource ls, Vector l) {
        Vector n = gp.geometry.getNormal(gp.point);

        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);

        double lightDistance = ls.getDistance(gp.point);
        var intersections = scene.getGeometries().findGeoIntersections(lightRay, lightDistance);
        if (intersections == null)
            return true;

        Double3 tr = Double3.ONE;
        for (var geo : intersections) {
            tr = tr.product(geo.geometry.getMaterial().getKt());
            if (tr.lowerThan(MIN_CALC_COLOR_K))
                return false;
        }

        return true;
    }
}