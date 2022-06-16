package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * a class of ambient light
 * @author Mikhal Levi and Eliana Grajower
 */
public class AmbientLight extends Light {
    /**
     * constructor
     * @param Ia intensity
     * @param Ka direction
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * default constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}
