package renderer;

import primitives.*;

/**
 * a class of a camera
 * @author Mikhal Levy & Eliana Grajower
 */
public class Camera {
    private Point location;
    private Vector v_t0;
    private Vector v_up;
    private Vector v_right;
    private double height;
    private double width;
    private double distance;

    /**
     * constructor
     * @param location {@link Point} a point on the camera
     * @param v_t0 {@link Vector} a vector the goes from the camera straight to the view plane.
     * @param v_up a vector that goes from the camera upwards
     */
    public Camera(Point location, Vector v_t0, Vector v_up) {
        this.location = location;
        this.v_t0 = v_t0.normalize();
        this.v_up = v_up.normalize();
        v_right = v_t0.crossProduct(v_up).normalize();
        if (v_t0.dotProduct(v_up) != 0)
           throw new IllegalArgumentException("the 2 vectors are not verticals to each other");
    }

    /**
     * getter
     * @return a location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * getter
     * @return a v_to
     */
    public Vector getV_t0() {
        return v_t0;
    }

    /**
     * getter
     * @return a v_up
     */
    public Vector getV_up() {
        return v_up;
    }

    /**
     * getter
     * @return a v_right
     */
    public Vector getV_right() {
        return v_right;
    }

    /**
     * getter
     * @return a height
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter
     * @return a width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter
     * @return a distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * sets the size of the view plane
     * @param width width of the view plane
     * @param height height of the view plane
     * @return the size of the view plane
     */
    public Camera setVPSize(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * sets the distance of the view plane from the camera
     * @param distance the distance of the view plane from the camera
     * @return the distance of the view plane from the camera
     */
    public Camera setVPDistance(double distance){
        this.distance = distance;
        return this;
    }

    /**
     * constructs the ray through the pixels
     * @param nX number of columns
     * @param nY number of lines
     * @param j index of pixels
     * @param i index of pixels
     * @return a constructed ray through the pixels
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        return null;
    }
}
