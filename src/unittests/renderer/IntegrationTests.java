package unittests.renderer;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTests {


    /**
     * Test helper function to count the intersections and compare with expected value
     *
     * @author Eliana Grajower & Mikhal Levy
     * @param cam camera for the test
     * @param geo 3D body to test the integration of the camera with
     * @param expected amount of intersections
     * @param nX
     * @param nY
     */
    private void assertCountIntersections(Camera cam, Intersectable geo, int expected, int nX, int nY) {
        int count = 0;

        List<Point> allpoints = null;


        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<>();
                    }
                    allpoints.addAll(intersections);
                }
                count += intersections == null ? 0 : intersections.size();
            }
        }
        assertEquals(expected, count, "Wrong amount of intersections");
    }
    /**
     * Integration tests of Camera Ray construction with Ray-Sphere intersections
     */
    @Test
    public void  CameraSphereIntersections() {
        Camera cam1 = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVPSize(3,3)
                .setVPDistance(1);;

        Camera cam2 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVPSize(3,3)
                .setVPDistance(1);


        // TC01: Small Sphere 2 points
        assertCountIntersections(cam1, new Sphere(new Point(0, 0, -3),1), 2, 3, 3);

        // TC02: Big Sphere 18 points
        assertCountIntersections(cam2, new Sphere( new Point(0, 0, -2.5),2.5), 18, 3, 3);

        // TC03: Medium Sphere 10 points
        assertCountIntersections(cam2, new Sphere( new Point(0, 0, -2),2), 10, 3, 3);

        // TC04: Inside Sphere 9 points
        assertCountIntersections(cam2, new Sphere( new Point(0, 0, -1),4), 9, 3, 3);

        // TC05: Beyond Sphere 0 points
        assertCountIntersections(cam1, new Sphere( new Point(0, 0, 1),0.5), 0, 3, 3);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Plane intersections
     */
    @Test
    public void CameraPlaneIntersections() {
        Camera cam = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVPSize(3,3)
                .setVPDistance(1);

        // TC01: Plane against camera 9 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 0, 1)), 9, 3, 3);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 2)), 9, 3, 3);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6, 3, 3);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(cam, new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)), 6, 3, 3);
    }

    /**
     * Integration tests of Camera Ray construction with Ray-Triangle intersections
     */
    @Test
    public void CameraTriangleIntersections() {
        Camera cam = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setVPSize(3,3)
                .setVPDistance(1);;

        // TC01: Small triangle 1 point
        assertCountIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -1, -2)), 1, 3, 3);

        // TC02: Medium triangle 2 points
        assertCountIntersections(cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2), new Point(0, -20, -2)), 2, 3, 3);
    }

}

