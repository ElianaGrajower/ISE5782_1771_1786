package unittests.primitives;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for primitives.Point class
 * @author Eliana Grajower & Mikhal Levy
 */
/**
 * Unit tests for geometries.Cylinder class
 * @author Eliana Grajower & Mikhal Levy
 */
public class PointTests {

  /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
      // ============ Equivalence Partitions Tests ==============
      Point p1 = new Point(1, 2, 3);

      // TC01: Test operations with points and vectors
      if (!(p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))))
        out.println("ERROR: Point + Vector does not work correctly");
    }



    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
      // ============ Equivalence Partitions Tests ==============
      Point p1 = new Point(1, 2, 3);

      // TC01: Test operations with points and vectors
      if (!new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)))
        out.println("ERROR: Point - Point does not work correctly");
    }

    @Test
    void testTestEquals() {
    }

    @Test
    void testTestToString() {
    }

    @Test
    void testDistanceSquared() {
    }

    @Test
    void testDistance() {
    }
}