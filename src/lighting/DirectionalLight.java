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
        this.direction = direction;
    }

    /**
     * getter for intensity
     *
     * @param point a point
     * @return
     */
    @Override
    public Color getIntensity(Point point) {
        return null;
    }

    /**
     * getter for light
     *
     * @param point point
     * @return
     */
    @Override
    public Vector getL(Point point) {
        return direction;
    }
}
