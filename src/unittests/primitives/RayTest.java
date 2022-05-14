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
        pointList = null;
        assertEquals(null,ray.findClosestPoint(pointList),
                "ERROR: Point - the list is empty");
        //TC11:Test if the nearest point is the first in the list
        List<Point> pointList1= new LinkedList<>();
        pointList1.add(point2);
        pointList1.add(point1);
        pointList1.add(point3);
        assertEquals(point2,ray.findClosestPoint(pointList1),
                "ERROR: Point - the nearest point is the first point");
        //TC12
        pointList1.clear();
        List<Point> pointList2 = new LinkedList<>();
        pointList2.add(point1);
        pointList2.add(point3);
        pointList2.add(point2);
        assertEquals(point2,ray.findClosestPoint(pointList2),
                "ERROR: Point - the nearest point is the last point");

        /** check this!!!!!!!!!!!!!!!!!!!! might be wrong i changed it!!!!!!!!!!!!!!!*/
    }
}