package geometries;

import primitives.*;

import java.util.List;

/**
 * This interface will serve all geometric classes
 * @author Mikhal Levy & Eliana Grajower
 */
public abstract class Geometry extends Intersectable{
    protected Color emission = Color.BLACK;
    /**
     * a get function
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * sets the emission
     * @param emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @param point
     * @return the normal to the vector in a specific point
     */
    public abstract Vector getNormal(Point point);    //is this supposed to be public????


}
