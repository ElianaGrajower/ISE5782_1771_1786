package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private Point position;
    private double kC = 1d;
    private double kL = 0d;
    private double kQ = 0d;
    /**
     * constructor
     * @param intensity
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
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
        return null;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }
}
