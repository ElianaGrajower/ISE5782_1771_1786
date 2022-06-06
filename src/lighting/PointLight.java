package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{
    private Point position;
    private Double3 kC = Double3.ONE;
    private Double3 kL = Double3.ZERO;
    private Double3 kQ = Double3.ZERO;
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
        double distance = point.distance(this.position);
        double disquer = point.distanceSquared(this.position);
        Double3 factor = kC.add(kL.scale(distance).add(kQ.scale(disquer)));
     //   return super.getIntensity().scale(1/(kC+kL*distance+kQ*disquer));
        return getIntensity().reduce(factor);
    }

    /**
     * getter for light
     *
     * @param point point
     * @return
     */
    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

    public PointLight setkC(Double3 kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(Double3 kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(Double3 kQ) {
        this.kQ = kQ;
        return this;
    }

    public PointLight setkC(double kC) {
        this.kC = new Double3(kC);
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = new Double3(kL);
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = new Double3(kQ);
        return this;
    }
}
