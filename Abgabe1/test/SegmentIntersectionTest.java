package computationalgeometry;

import computationalgeometry.Tools.Line;
import computationalgeometry.Tools.Segment;
import computationalgeometry.Tools.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduard
 */
public class SegmentIntersectionTest {
    
    public SegmentIntersectionTest() {
    }

    @Test
    public void testfindIntersections1() throws Exception {
        System.out.println("Test1");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5, -5), new Point(5, 5)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    @Test
    public void testfindIntersections2() throws Exception {
        System.out.println("Test2");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5, 0), new Point(5, 5)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    @Test
    public void testfindIntersections3() throws Exception {
        System.out.println("Test3");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5, 0.0001), new Point(5, 5)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(0, counter, 0.0);
    }
    @Test
    public void testfindIntersections4() throws Exception {
        System.out.println("Test4");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5, -0.001), new Point(5, 5)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    @Test
    public void testfindIntersections5() throws Exception {
        System.out.println("Test5");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5, 0), new Point(5, 0.1)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    @Test
    public void testfindIntersections6() throws Exception {
        System.out.println("Test6");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(10.001, 0), new Point(20, 0)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(0, counter, 0.0);
    }
    @Test
    public void testfindIntersections7() throws Exception {
        System.out.println("Test7");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5, 0), new Point(5, 0)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    @Test
    public void testfindIntersections8() throws Exception {
        System.out.println("Test8");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5, 0.01), new Point(5, 0)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    @Test
    public void testfindIntersections9() throws Exception {
        System.out.println("Test9");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5, -0.01), new Point(5, 0.01)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    @Test
    public void testfindIntersections10() throws Exception {
        System.out.println("Test10");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 10)));
        lines.add(new Segment(new Point(5, 4.9), new Point(10, 9.9)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(0, counter, 0.0);
    }
    
    @Test
    public void testfindIntersections11() throws Exception {
        System.out.println("Test11");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 10)));
        lines.add(new Segment(new Point(0,0), new Point(10, 10)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    @Test
    public void testfindIntersections12() throws Exception {
        System.out.println("Test12");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(5,0), new Point(6, 10)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    
    @Test
    public void testfindIntersections13() throws Exception {
        System.out.println("Test13");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(10,0), new Point(10, 0)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    
    @Test
    public void testfindIntersections14() throws Exception {
        System.out.println("Test14");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(20,0), new Point(300, 0)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(0, counter, 0.0);
    }
    
    @Test
    public void testfindIntersections15() throws Exception {
        System.out.println("Test15");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(10, 0)));
        lines.add(new Segment(new Point(0,0.0001), new Point(10, 0.0001)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(0, counter, 0.0);
    }
    
    @Test
    public void testfindIntersections16() throws Exception {
        System.out.println("Test16");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0, 0), new Point(0, 0)));
        lines.add(new Segment(new Point(0,0), new Point(0, 0)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    
    @Test
    public void testfindIntersections17() throws Exception {
        System.out.println("Test17");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(10, 10), new Point(10, 10)));
        lines.add(new Segment(new Point(10,10), new Point(10, 10)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter, 0.0);
    }
    
    @Test
    public void testfindIntersections18() throws Exception {
        System.out.println("Test18");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(69.336, 16.408), new Point(69.0816, 16.9404)));
        lines.add(new Segment(new Point(9.373 ,61.876 ), new Point(10.2139 , 62.2071)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(0, counter, 0.0);
    }
    @Test
    public void testfindIntersections19() throws Exception {
        System.out.println("Test19");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(13.291, 12.881), new Point(13.3193, 12.8189)));
        lines.add(new Segment(new Point(13.125,12.808), new Point(13.5814,12.8336)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(0, counter, 0.0);
    }
    
    @Test
    public void testfindIntersections20() throws Exception {
        System.out.println("Test20");
        final List<Line> lines = new ArrayList<>();
        lines.add(new Segment(new Point(0,0), new Point(10,10)));
        lines.add(new Segment(new Point(0,0), new Point(0,0)));
        
        final int counter = SegmentIntersection.findIntersections(lines);
        assertEquals(1, counter);
    }
    
   
}
