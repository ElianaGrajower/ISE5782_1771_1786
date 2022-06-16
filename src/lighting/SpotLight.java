package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * a class of spotLights
 * @author Miachal Levi and eliana Grajower
 */
public class SpotLight extends PointLight{
    private Vector direction;
    private double narrowBeam = 0d;

    /**
     * constructor
     * @param intensity color
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * getter for intensity
     * @param point a point
     * @return color
     */
    public Color getIntensity(Point point)
    {
        double max= Math.max(0,direction.dotProduct(getL(point)));
        return super.getIntensity(point).scale(max);
    }

    /**
     * sets a narrow beam
     * @param narrowBeam a narrow beam of light
     * @return spotLight
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }
}
