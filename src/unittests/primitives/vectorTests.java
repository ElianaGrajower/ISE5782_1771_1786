package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
/**
 * Unit tests for primitives.Point class
 * @author Eliana Grajower & Mikhal Levy
 */
/**
 * Unit tests for geometries.Cylinder class
 * @author Eliana Grajower & Mikhal Levy
 */
public class vectorTests {
    @Test
public void testConstructor(){
        // =============== Boundary Values Tests ==================
        // TC10: test zero vector
        try {
            new Vector(0, 0, 0);
            out.println("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {
        }
    }

    @Test
    void testTestEquals() {
    }

    @Test
    void testAdd() {
    }
    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:test length..
       Vector v1 = new Vector(1, 2, 3);
        if (!isZero(v1.lengthSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");

    }
    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test length
        if (!isZero(new Vector(0, 3, 4).length() - 5))
            out.println("ERROR: length() wrong value");
    }

    @Test
    void testScale() {
    }

     /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);
        Vector v3 = new Vector(-2, -4, -6);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test if Dot-Product has the wrong value
        if (!isZero(v1.dotProduct(v2) + 28))
            out.println("ERROR: dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: Test Dot-Product is not zero
        if (!isZero(v1.dotProduct(v3)))
            out.println("ERROR: dotProduct() for orthogonal vectors is not zero");

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        // TC01: Test that the vector has been normalized
        if (!isZero(u.length() - 1))
            out.println("ERROR: the normalized vector is not a unit vector");
        // TC02:test that the vectors are co-lined
        try {
            v.crossProduct(u);
            out.println("ERROR: the normalized vector is not parallel to the original one");
        } catch (Exception e) {
        }
        // TC03: Test that the vector has been normalized
        if (v.dotProduct(u) < 0)
            out.println("ERROR: the normalized vector is opposite to the original one");
    }




    @Test
    void testTestToString() {
    }
}