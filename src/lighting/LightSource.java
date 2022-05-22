package lighting;

import primitives.*;

/**
 * @author Mikhal Levi & Eliana Grajower
 */
public interface LightSource {
    /**
     * getter for intensity
     * @param point a point
     * @return
     */
    public Color getIntensity(Point point);

    /**
     * getter for light
     * @param point point
     * @return
     */
    public Vector getL(Point point);
    double getDistance(Point point);
}
