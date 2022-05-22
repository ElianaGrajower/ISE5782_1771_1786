package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;
    /**
     * constructor
     * @param intensity
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * getter for intensity
     *
     * @param point a point
     * @return
     */
    @Override
    public Color getIntensity(Point point) {
        return super.getIntensity();
    }

    /**
     * getter for light
     *
     * @param point point
     * @return
     */
    @Override
    public Vector getL(Point point) {
        return direction.normalize();
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }
}
