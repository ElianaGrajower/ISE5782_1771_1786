package lighting;

import primitives.*;

import java.util.List;

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

    /**
     * return list of vectors' the beam
     * @param p
     * @param radius
     * @param amount
     * @return
     */
    public List<Vector> getBeamL(Point p, double radius, int amount);
}
