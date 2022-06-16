package primitives;

import java.util.Objects;

/**
 * a class of points
 * @author Miachal Levi and eliana Grajower
 */
public class Point {

    final Double3 xyz;
    /**
     * static value-the zero point
     */
    public final static Point ZERO=new Point(0d,0d,0d);

    /**
     *constructor
     * @param p a point
     */
    public Point(Point p)
    {
        this.xyz=p.xyz;
    }
    /**
     * constructor
     * @param x part of a point
     * @param y part of a point
     * @param z part of a point
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    /**
     * constructor
     * @param xyz a point
     */
    public Point(Double3 xyz){
        this.xyz = xyz;
    }

    /**
     * adds a vector to a point
     * @param vector a vector
     * @return a new point
     */
    public Point add(Vector vector){
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * creates a vector out of 2 points
     * @param point a point
     * @return the vector that it creates
     */
    public Vector subtract(Point point){
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * checks if objects are equal to each other
     * @param object an object
     * @return true or false
     */
    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Point point = (Point)object;
        return xyz.equals(point.xyz);
    }

    /**
     * prints a point type
     * @return a string
     */
    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * calculates the distance between points squared
     * @param point a point
     * @return the distance squared of the points
     */
    public double distanceSquared(Point point){
        double x=this.xyz.subtract(point.xyz).d1 * this.xyz.subtract(point.xyz).d1;
        double y=this.xyz.subtract(point.xyz).d2 * this.xyz.subtract(point.xyz).d2;
        double z=this.xyz.subtract(point.xyz).d3 * this.xyz.subtract(point.xyz).d3;
        return x+y+z;
    }

    /**
     * calculates the distance between the squares
     * @param point a point
     * @return the distance
     */
    public double distance(Point point){
        return Math.sqrt(distanceSquared(point));
    }

    /**
     * get for x
     * @return double for x
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * get for y
     * @return double for y
     */
    public double getY() {
        return xyz.d2;
    }
    /**
     * get for z
     * @return double for z
     */
    public double getZ() {
        return xyz.d3;
    }
}
