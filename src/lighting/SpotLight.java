package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector direction;
    private double narrowBeam = 0d;

    /**
     * constructor
     * @param intensity
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * getter for intensity
     * @param point a point
     * @return
     */
    public Color getIntensity(Point point)
    {
        double max= Math.max(0,direction.dotProduct(getL(point)));
        return super.getIntensity(point).scale(max);
    }

    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }
}
