package primitives;

public class Ray {

    private final Point p0;
    private final Vector dir;

    /**
     * constructor
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize(); //normalize the vector
    }

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
    /**
     * checks if objects are equal to each other
     * @param obj
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray ray = (Ray)obj;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * prints a ray type
     * @return
     */
    @Override
    public String toString() {
        return p0.toString() + dir.toString();
    }

}

