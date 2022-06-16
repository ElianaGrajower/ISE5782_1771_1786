package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;
/**
 * Unit tests for primitives.Vector class
 * @author Eliana Grajower and Mikhal Levy
 */
public class vectorTests {

    @Test
    public void testConstructor(){
        // =============== Boundary Values Tests ==================
        // TC10: test zero vector
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(0, 0, 0),
                "ERROR: zero vector does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:test length..
        Vector v1 = new Vector(1, 2, 3);
        assertTrue (isZero(v1.lengthSquared() - 14),
                "ERROR: lengthSquared() wrong value");

    }
    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test length
        assertTrue (isZero(new Vector(0, 3, 4).length() - 5),
                "ERROR: length() wrong value");
    }

     /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple dotProduct test
        assertEquals(-28d, v1.dotProduct(v2), 0.00001, "dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: dotProduct for orthogonal vectors
        assertEquals(
                0d, v1.dotProduct(v3),
                0.00001,
                "dotProduct() for orthogonal vectors is not zero");
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
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,
                "crossProduct() wrong result length");

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
        assertTrue (isZero(u.length() - 1),
                "ERROR: the normalized vector is not a unit vector");
        // TC02:test that the vectors are co-lined
        assertThrows(IllegalArgumentException.class,
            ()-> v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");
        // TC03: Test that the vector has been normalized
        assertFalse (v.dotProduct(u) < 0,
                "ERROR: the normalized vector is opposite to the original one");
    }
}