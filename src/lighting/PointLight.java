package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.isZero;

/**
 * a class of point lights
 * @author Miachal Levi and eliana Grajower
 */
public class PointLight extends Light implements LightSource{
    private Point position;
    private Double3 kC = Double3.ONE;
    private Double3 kL = Double3.ZERO;
    private Double3 kQ = Double3.ZERO;
    private static final Random RND = new Random();
    /**
     * constructor
     * @param intensity color
     * @param position point
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * getter for intensity
     * @param point a point
     * @return color
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
     * @param point point
     * @return vector
     */
    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

    /**
     * set the kc -intensity
     * @param kC intensity
     * @return PointLight
     */
    public PointLight setkC(Double3 kC) {
        this.kC = kC;
        return this;
    }

    /**
     * set the kl
     * @param kL Double3
     * @return PointLight
     */
    public PointLight setkL(Double3 kL) {
        this.kL = kL;
        return this;
    }

    /**
     * set the kq
     * @param kQ Double3
     * @return PointLight
     */
    public PointLight setkQ(Double3 kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * set kc
     * @param kC double
     * @return PointLight
     */
    public PointLight setkC(double kC) {
        this.kC = new Double3(kC);
        return this;
    }

    /**
     * set the kl
     * @param kL double
     * @return PointLight
     */
    public PointLight setkL(double kL) {
        this.kL = new Double3(kL);
        return this;
    }

    /**
     * set the kq
     * @param kQ opacity
     * @return the pointLight
     */
    public PointLight setkQ(double kQ) {
        this.kQ = new Double3(kQ);
        return this;
    }

    /**
     * return a beam of ray
     * @param p point
     * @param radius radius
     * @param amount int
     * @return List<Vector>
     */
    @Override
    public List<Vector> getBeamL(Point p, double radius, int amount) {
        if (p.equals(position)) {
            return null;
        }
        LinkedList<Vector> beam = new LinkedList<>();

        //from pointlight position to p point
        Vector v = this.getL(p);
        beam.add(v);
        if (amount <= 1) {
            return beam;
        }

        double distance = this.position.distance(p);

        if (isZero(distance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point lightHead = new Point(v.getX(),v.getY(),v.getZ());
        Vector normX;

        // if v._head == (0,0,w) then normX.head ==(-w,0,0)
        // otherwise normX.head == (-y,x,0)
        if (isZero(lightHead.getX()) && isZero(lightHead.getY())) {
            normX = new Vector(lightHead.getZ() * -1, 0, 0).normalize();
        } else {
            normX = new Vector(lightHead.getY() * -1, lightHead.getX(), 0).normalize();
        }

        Vector normY = v.crossProduct(normX).normalize();
        double cosTheta;
        double sinTheta;

        double d;
        double x;
        double y;

        for (int counter = 0; counter < amount; counter++) {
            Point newPoint = new Point(this.position);
            // randomly coose cosTheta and sinTheta in the range (-1,1)
            cosTheta = 2 * RND.nextDouble() - 1;
            sinTheta = Math.sqrt(1d - cosTheta * cosTheta);

            //d ranged between -radius and  +radius
            d = radius * (2 * RND.nextDouble() - 1);
            //d ranged between -radius and  +radius
            if (isZero(d)) { //Thanks to Michael Shachor
                counter--;
                continue;
            }
            x = d * cosTheta;
            y = d * sinTheta;

            if (!isZero(x)) {
                newPoint = newPoint.add(normX.scale(x));
            }
            if (!isZero(y)) {
                newPoint = newPoint.add(normY.scale(y));
            }
            beam.add(p.subtract(newPoint).normalize());
        }
        return beam;

    }
}
