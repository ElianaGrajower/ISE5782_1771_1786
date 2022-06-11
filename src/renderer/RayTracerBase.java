package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

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
     *
     * @param rays
     * @return
     */
    public abstract Color traceRays(List<Ray> rays);
}
