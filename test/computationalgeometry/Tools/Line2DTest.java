package computationalgeometry.Tools;

import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduard
 */
public class Line2DTest {
    
    public Line2DTest() {
    }
    
    
    @Test
    public void testContains() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10, 0));
        assertTrue(line2D.isOnLine(new Point(5, 0)));
    }
    
    @Test
    public void testContains2() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10, 10));
        assertTrue(line2D.isOnLine(new Point(5, 5)));
    }
    
    @Test
    public void testContains3() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10, 10));
        assertFalse(line2D.isOnLine(new Point(5, 6)));
    }
    
    @Test
    public void testContains4() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10, 10));
        assertFalse(line2D.isOnLine(new Point(11, 11)));
    }
    
    @Test
    public void testContains5() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10, 10));
        assertTrue(line2D.isOnLine(new Point(10, 10)));
    }
    
    @Test
    public void testContains6() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10, 10));
        assertTrue(line2D.isOnLine(new Point(0, 0)));
    }
    
    @Test
    public void testGetIntersection1NumericalAccVertical() throws Exception {
        Segment line2D = new Segment(new Point(81.7675, 53.4569), new Point(81.925, 53.053));
        Segment line2D2 = new Segment(new Point(81.88, 53.0132), new Point(81.88, 53.432));
        assertEquals(Optional.of(new Point(81.88, 53.168400000000005)),line2D.getIntersection(line2D2));
    }
    
    @Test
    public void testGetIntersection2NumericalAccVertical() throws Exception {
        Segment line2D = new Segment(new Point(81.7675, 53.4569), new Point(81.925, 53.053));
        Segment line2D2 = new Segment(new Point(81.88, 53.0132), new Point(81.88, 53.432));
        assertEquals(Optional.of(new Point(81.88, 53.168400000000005)),line2D2.getIntersection(line2D));
    }
    
    
    @Test
    public void testGetIntersection3Vertical() throws Exception {
        Segment line2D = new Segment(new Point(10, 0), new Point(10, 50));
        Segment line2D2 = new Segment(new Point(10.00001,0), new Point(900.00001, 60));
        assertFalse(line2D2.getIntersection(line2D).isPresent());
    }
    
    @Test
    public void testGetIntersection4NumericalAccHorizontal() throws Exception {
        Segment line2D = new Segment(new Point(94.8619, 24.083), new Point(95.188, 24.083));
        Segment line2D2 = new Segment(new Point(95.188, 24.083), new Point(94.9515, 24.0113));
        assertEquals(Optional.of(new Point(95.188, 24.083)),line2D2.getIntersection(line2D));//https://www.wolframalpha.com/input/?i=Plot%2894.8619%2C+24.083%29%2895.188%2C+24.083%29%2895.188%2C+24.083%29%2894.9515%2C+24.0113%29
    }
    
    @Test
    public void testGetIntersection5NumericalAccHorizontal() throws Exception {
        Segment line2D = new Segment(new Point(94.8619, 24.083), new Point(95.188, 24.083));
        Segment line2D2 = new Segment(new Point(95.188, 24.083), new Point(94.9515, 24.0113));
        assertEquals(Optional.of(new Point(95.188, 24.083)),line2D.getIntersection(line2D2));//https://www.wolframalpha.com/input/?i=Plot%2894.8619%2C+24.083%29%2895.188%2C+24.083%29%2895.188%2C+24.083%29%2894.9515%2C+24.0113%29
    }
    
    @Test
    public void testGetIntersection6StartEnd() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,0));
        Segment line2D2 = new Segment(new Point(10, 0), new Point(20,0));
        assertEquals(new Point(10,0),line2D.getIntersection(line2D2).get());
    }
    
    @Test
    public void testGetIntersection7Vertical() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(5, 5.001), new Point(5,10));
        assertEquals(Optional.empty(),line2D.getIntersection(line2D2));
    }
    
    @Test
    public void testGetIntersection8VerticalHorizontal() throws Exception {
        Segment line2D = new Segment(new Point(0, 5), new Point(10,5));
        Segment line2D2 = new Segment(new Point(5, 5.001), new Point(5,10));
        assertEquals(Optional.empty(),line2D.getIntersection(line2D2));
    }
    
    @Test
    public void testGetIntersection9VerticalHorizontal() throws Exception {
        Segment line2D = new Segment(new Point(0, 5), new Point(10,5));
        Segment line2D2 = new Segment(new Point(-0.001, 0), new Point(-0.001,10));
        assertEquals(Optional.empty(),line2D.getIntersection(line2D2));
    }
    
    @Test
    public void testGetIntersection10Length0() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(0, 0), new Point(0,0));
        assertEquals(Optional.of(new Point(0,0)),line2D.getIntersection(line2D2));
    }
    @Test
    public void testGetIntersection11Length0() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(5, 5), new Point(5,5));
        assertEquals(Optional.of(new Point(5,5)),line2D.getIntersection(line2D2));
    }
    @Test
    public void testGetIntersection12Length0() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(-0.1, -0.1), new Point(-0.1,-0.1));
        assertEquals(Optional.empty(),line2D.getIntersection(line2D2));
    }
    
    @Test
    public void test16OverlapPoint() throws Exception {
        Segment line2D = new Segment(new Point(10, 10), new Point(10,10));
        Segment line2D2 = new Segment(new Point(10, 10), new Point(10.1, 10.1));
        assertEquals(Optional.of(new Point(10,10)),line2D.getIntersection(line2D2));
    }
    
    @Test
    public void test13OverlapPoint() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(5, 5), new Point(15,15));
        assertEquals(Optional.of(new Point(5,5)),line2D.getOverlapPoint(line2D2));
    }
    
    @Test
    public void test14OverlapPoint() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(5, 5), new Point(15,15));
        assertEquals(Optional.of(new Point(5,5)),line2D.getOverlapPoint(line2D2));
    }
    
    @Test
    public void test15OverlapPoint() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(10, 10), new Point(15,15));
        assertEquals(Optional.of(new Point(10,10)),line2D.getOverlapPoint(line2D2));
    }
    
    @Test
    public void testGetIntersection16Overlapping() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,0));
        Segment line2D2 = new Segment(new Point(5, 0), new Point(20,0));
        assertEquals(new Point(5,0),line2D.getIntersection(line2D2).get());
    }
    
    @Test
    public void testGetIntersection17Overlapping() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(5, 5), new Point(20,20));
        assertEquals(new Point(5,5),line2D.getIntersection(line2D2).get());
    }
    
    @Test
    public void testGetIntersection18Overlapping() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(5, 5), new Point(10,10));
        assertEquals(new Point(5,5),line2D.getIntersection(line2D2).get());
    }
    
    @Test
    public void testGetIntersection19Overlapping() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(9, 9), new Point(10,10));
        assertEquals(new Point(9,9),line2D.getIntersection(line2D2).get());
    }
    
    @Test
    public void testGetIntersection20Overlapping() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(9, 9), new Point(11,11));
        assertEquals(new Point(9,9),line2D.getIntersection(line2D2).get());
    }
    
    @Test
    public void testGetIntersection21() throws Exception {
        Segment line2D = new Segment(new Point(0, 0), new Point(10,10));
        Segment line2D2 = new Segment(new Point(0, 0), new Point(10,0));
        assertEquals(new Point(0,0),line2D.getIntersection(line2D2).get());
    }
    
}