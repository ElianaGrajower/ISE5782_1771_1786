package unittests.geometries;

import geometries.Plane;
import geometries.Sphere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Point class
 * @author Eliana Grajower & Mikhal Levy
 */
/**
 * Unit tests for geometries.Cylinder class
 * @author Eliana Grajower & Mikhal Levy
 */
public class SphereTest {



    @Test
    void testGetCenter() {
    }

    @Test
    void testGetRadius() {
    }

    @Test
    void testTestToString() {
    }

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: checks if the normal is well calculated
        Sphere sph = new Sphere(new Point(0, 0, 1), 1.0);
        assertEquals(new Vector(0, 0, 1), sph.getNormal(new Point(0, 0, 2)), "Bad normal to sphere");
        // TC02: checks if the normal length is equal to 1
        assertEquals(1, sph.getNormal(new Point(2,0,1)).length(), "the normal is not equal to 1");

    }
}