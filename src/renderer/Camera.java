package renderer;

import primitives.Point;
import primitives.Vector;

public class Camera {
    private Point location;
    private Vector v_t0;
    private Vector v_up;
    private Vector v_right;
    private double height;
    private double width;
    private double distance;

    public Camera(Point location, Vector v_t0, Vector v_up) {
        this.location = location;
        this.v_t0 = v_t0;
        this.v_up = v_up;
        if(v_t0.dotProduct(v_up) != 0)
           throw new IllegalArgumentException("the 2 vectors are not verticals to each other");
    }

    public Point getLocation() {
        return location;
    }

    public Vector getV_t0() {
        return v_t0;
    }

    public Vector getV_up() {
        return v_up;
    }

    public Vector getV_right() {
        return v_right;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }
}
