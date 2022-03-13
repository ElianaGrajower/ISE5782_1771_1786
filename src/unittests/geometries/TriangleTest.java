package unittests.geometries;

import geometries.Plane;
import geometries.Triangle;
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
public class TriangleTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testTestToString() {
    }
    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test if normal is well calculated
        Triangle pl = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3),
                pl.getNormal(new Point(0, 0, 1)),
                "Bad normal to triangle");

        // TC02: checks if the normal length is equal to 1
        assertEquals(1, pl.getNormal(null).length(),"the normal is not equal to 1");

    }
}