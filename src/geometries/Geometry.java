package geometries;

import primitives.*;

import java.util.List;

/**
 * This interface will serve all geometric classes
 * @author Mikhal Levy and Eliana Grajower
 */
public abstract class Geometry extends Intersectable{
    protected Color emission = Color.BLACK;
    private Material material = new Material();
    /**
     * a get function
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * getter for material
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * sets the emission
     * @param emission a color
     * @return geometry
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @param point a point
     * @return the normal to the vector in a specific point
     */
    public abstract Vector getNormal(Point point);    //is this supposed to be public????

    /**
     * a material setter that returns a geometry
     * @param material a material
     * @return geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
