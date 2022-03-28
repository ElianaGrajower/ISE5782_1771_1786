package primitives;

import java.util.Objects;

public class Point {

    final Double3 xyz;
    public final static Point ZERO=new Point(0d,0d,0d);

    /**
     * constructor
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x,y,z);
    }

    /**
     * constructor
     * @param xyz
     */
    public Point(Double3 xyz){
        this.xyz = xyz;
    }

    /**
     * adds a vector to a point
     * @param vector
     * @return a new point
     */
    public Point add(Vector vector){
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * creates a vector out of 2 points
     * @param point
     * @return the vector that it creates
     */
    public Vector subtract(Point point){
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * checks if objects are equal to each other
     * @param object
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
     * @return
     */
    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * calculates the distance between points squared
     * @param point
     * @return the distance squared of the points
     */
    public double distanceSquared(Point point){
        final double x1=xyz.d1;
        final double y1=xyz.d2;
        final double z1=xyz.d3;
        final double x2=point.xyz.d1;
        final double y2=point.xyz.d2;
        final double z2=point.xyz.d3;
       return((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1))*((z2-z1)*(z2-z1));
    }

    /**
     * calculates the distance between the squares
     * @param point
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
