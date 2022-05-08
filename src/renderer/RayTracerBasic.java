package renderer;

import geometries.Geometry;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

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
    private Color calcColor(Point point)
    {
        return scene.getAmbienLight().getIntensity().add(Geometry.getEmission());
        //return scene.getAmbientLight().getIntensity();
    }
    /**
     * abstract method that receives a ray.
     * @param ray
     * @return a color
     */
    @Override
    public Color traceRay(Ray ray) {

            List<Point> intersection = scene.geometries.findIntersections(ray);
            if (intersection == null)
            {
                return scene.getBackground();
            }
           Point closestPoint=ray.findClosestPoint(intersection);
            return calcColor(closestPoint);
        }
    }


