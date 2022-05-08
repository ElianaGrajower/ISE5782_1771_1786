package geometries;

import primitives.*;

import java.util.List;

/**
 * This interface will serve all geometric classes
 * @author Mikhal Levy & Eliana Grajower
 */
public interface Geometry extends Intersectable{
     Color emission =Color.BLACK;



    public static Color getEmission()
    {
        return emission;
    }
    /**
     * @param point
     * @return the normal to the vector in a specific point
     */
    Vector getNormal(Point point);    //is this supposed to be public????


}
