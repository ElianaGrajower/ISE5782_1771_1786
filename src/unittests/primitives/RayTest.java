package unittests.primitives;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {
        // // ============ Equivalence Partitions Tests ==============
        Ray ray=new Ray(new Point(1,1,1),new Vector(1,2,3)) ;
        Point point1= new Point(4,4,4);
        Point point2= new Point(2,2,2);
        Point point3= new Point(5,5,5);
        List<Point> pointList= new LinkedList<>();;
        pointList.add(point1);
        pointList.add(point2);
        pointList.add(point3);
        // TC01: Test operations with list of points and ray
        assertEquals(point2,ray.findClosestPoint(pointList),
                "ERROR: Point - Point isn't the closest to the ray head");
        // =============== Boundary Values Tests ==================

        //TC10:Test an empty list of points
        pointList.clear();
        pointList=null;
        assertEquals(null,ray.findClosestPoint(pointList),
                "ERROR: Point - the list is empty");
        //TC11:Test if the nearest point is the first in the list
        pointList.add(point2);pointList.add(point1);pointList.add(point3);
        assertEquals(point2,ray.findClosestPoint(pointList),
                "ERROR: Point - the nearest point is the first point");
        //TC12
        pointList.clear();
        pointList.add(point1);pointList.add(point3);pointList.add(point2);
        assertEquals(point3,ray.findClosestPoint(pointList),
                "ERROR: Point - the nearest point is the last point");
    }
}