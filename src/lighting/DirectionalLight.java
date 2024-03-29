package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * a class of directional lights
 * @author Eliana Grajower and Mikhal Levi
 */
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
     * @param point a point
     * @return color
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

    /**
     *
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

    /**
     *return list of vector of the value of direction
     * @param dummyPoint3D
     * @param dummyRadius
     * @param dummyInt
     * @return
     */
    @Override
    public List<Vector> getBeamL(Point dummyPoint3D, double dummyRadius, int dummyInt) {
        return List.of(new Vector(new Double3(direction.getX(),direction.getY(),direction.getZ())));
    }
}
