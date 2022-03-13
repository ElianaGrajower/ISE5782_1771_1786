package unittests.geometries;

import geometries.Plane;
import geometries.Polygon;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Point class
 * @author Eliana Grajower & Mikhal Levy
 */
/**
 * Unit tests for geometries.Cylinder class
 * @author Eliana Grajower & Mikhal Levy
 */
public class PlaneTest {
    /**
     *test for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    public void testConstructor() {

        // ============ Boundary Values Tests ==============

        // TC11: the two first points are equal

        try {
                new Plane(
                        new Point(1, 0, 0),
                        new Point(1, 0, 0),
                        new Point(4, 0, 0));
                fail("constructor created plane with two same points");
            } catch (IllegalArgumentException e) {
            }
        // TC11: the points are o the same line
         try {
                new Plane(
                        new Point(1, 0, 0),
                        new Point(2, 0, 0),
                        new Point(4, 0, 0));
                fail("constructor created plane with 3 point on the same line");
            } catch (IllegalArgumentException e) {
            }
        }



    @Test
    void testGetQ0() {
    }

    @Test
    void testTestToString() {
    }
    /**
     *tests for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {

        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: checks if the normal length is equal to 1
        assertEquals(1, pl.getNormal(null).length(),"the normal is not equal to 1");

        // TC02: checks if the normal is well calculated
                double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");

    }


    @Test
    void testTestGetNormal() {
    }


}