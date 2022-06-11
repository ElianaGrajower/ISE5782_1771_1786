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

    protected boolean softShadows =false;
    protected double beamRadius =20d;//אלומת אור

    public void setSoftShadows(boolean softShadows) {
        this.softShadows = softShadows;
    }

    public void setBeamRadius(double beamRadius) {
        this.beamRadius = beamRadius;
    }

    public boolean isSoftShadows() {
        return softShadows;
    }

    public double getBeamRadius() {
        return beamRadius;
    }
}
