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
public class PointTests {

  /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
      // ============ Equivalence Partitions Tests ==============
      Point p1 = new Point(1, 2, 3);

      // TC01: Test operations with points and vectors
      assertEquals(p1.add(new Vector(-1, -2, -3)),new Point(0, 0, 0),
              "ERROR: Point + Vector does not work correctly");

    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
      // ============ Equivalence Partitions Tests ==============
      Point p1 = new Point(1, 2, 3);

      // TC01: Test operations with points and vectors
      assertEquals(new Vector(1, 1, 1),(new Point(2, 3, 4).subtract(p1)),
              "ERROR: Point - Point does not work correctly");
    }

}