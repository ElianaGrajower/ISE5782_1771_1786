package unittests.geometries;

import geometries.Plane;
import geometries.Tube;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
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
public class TubeTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAxisRay() {
    }

    @Test
    void testGetRadius() {
    }

    @Test
    void testTestToString() {
    }

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {

            // ============ Equivalence Partitions Tests ==============
            // TC01: There is a simple single test here
            Tube tube = new Tube( new Ray(new Point(0, -1, 0),new Vector(0, 0, 1)),1.0);
            Vector normal = tube.getNormal(new Point(0, 0.5, 2)).normalize();
            assertEquals(new Vector(0, 0, 1).length(), normal.length(), "Bad normal to tube");
        }
    }
