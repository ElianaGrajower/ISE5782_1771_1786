package renderer;

import primitives.*;
import scene.Scene;

/**
 * @author Mikhal Levy & Eliana Grajower
 */

public abstract class RayTracerBase {

    protected Scene scene;

    /**
     * constructor
     * @param scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * abstract method that receives a ray.
     * @param ray
     * @return a color
     */
    public abstract Color traceRay(Ray ray);
}
