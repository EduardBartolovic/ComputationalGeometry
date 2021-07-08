package computationalgeometry;

import SweepLine.BentleyOttmann;
import computationalgeometry.Tools.SegmentExtended;
import computationalgeometry.Tools.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduard
 */
public class SegmentIntersectionTestOptimized {
    
    public SegmentIntersectionTestOptimized() {
    }

    @Test
    public void testSweepLine1() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(10, 10), new Point(0, 0)));
        lines.add(new SegmentExtended(new Point(0,10), new Point(10,0)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections(); 
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine2() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(10, 10), new Point(0, 0)));
        lines.add(new SegmentExtended(new Point(9,10), new Point(11,10)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(10, 10));
        final List<Point> have = finder.findIntersections();     
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine3Vertical() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,0), new Point(5,10))); //Vertical line
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections();    
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine4() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,6), new Point(5,10))); //Vertical line
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        
        final List<Point> have = finder.findIntersections();     
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine5() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,6), new Point(5,10))); //Vertical line
        lines.add(new SegmentExtended(new Point(5,4), new Point(5,0))); //Vertical line
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        final List<Point> have = finder.findIntersections();     
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine6Verticallines() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,6), new Point(5,10))); //Vertical line
        lines.add(new SegmentExtended(new Point(5,5), new Point(5,0))); //Vertical line
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine7VerticallinesNoHit() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,6), new Point(5,10))); //Vertical line
        lines.add(new SegmentExtended(new Point(5,4.5), new Point(5,0))); //Vertical line
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine7() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(10, 10), new Point(0, 0)));
        lines.add(new SegmentExtended(new Point(10,10), new Point(10,1)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(10, 10));
        final List<Point> have = finder.findIntersections(); 
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine8() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(10, 10), new Point(0, 0)));
        lines.add(new SegmentExtended(new Point(10,10), new Point(10,0)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(10, 10));
        final List<Point> have = finder.findIntersections();    
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine9MeetAtEndPoint() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(5, 5)));
        lines.add(new SegmentExtended(new Point(0,10), new Point(5,5))); 
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine10MeetAtEndPointVertical() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(0, 5)));
        lines.add(new SegmentExtended(new Point(0,10), new Point(0,5))); 
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(0, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    

    
    @Test
    public void testSweepLine12SegmentWithLength0() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,5), new Point(5,5)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }

    
    @Test
    public void testSweepLine14VerticalAnd3Horizointal() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(5,0.1), new Point(5,15)));//Vertical
        lines.add(new SegmentExtended(new Point(0, 10), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(0,3), new Point(10,3)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,0)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 3));
        want.add(new Point(5, 10));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine14VerticalAnd4Horizointal() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(5,0.1), new Point(5,15)));//Vertical
        lines.add(new SegmentExtended(new Point(0, 10), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(0,3), new Point(10,3)));
        lines.add(new SegmentExtended(new Point(1,9), new Point(10,9)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,0)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 3));
        want.add(new Point(5, 9));
        want.add(new Point(5, 10));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine15EasyTriangle() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,5), new Point(10,5)));
        lines.add(new SegmentExtended(new Point(1, 1), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(7,10), new Point(12,0)));//https://www.wolframalpha.com/input/?i=plot%28%280%2C5%29%2810%2C5%29%281%2C1%29%2810%2C+10%29%287%2C10%29%2812%2C0%29%29
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        want.add(new Point(8, 8));
        want.add(new Point(9.5, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine15Triangle() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,5), new Point(10,5)));
        lines.add(new SegmentExtended(new Point(1, 1), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,10), new Point(11,0)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        want.add(new Point(6.875, 6.875));
        want.add(new Point(8, 5));
        final List<Point> have = finder.findIntersections();//https://www.wolframalpha.com/input/?i=plot%28%280%2C5%29%2810%2C5%29%281%2C1%29%2810%2C+10%29%285%2C10%29%2811%2C0%29%29
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine16Triangle() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,5), new Point(10,5)));
        lines.add(new SegmentExtended(new Point(1, 1), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,7), new Point(8,4)));//https://www.wolframalpha.com/input/?i=plot%28%280%2C5%29%2810%2C5%29%281%2C1%29%2810%2C+10%29%285%2C7%29%288%2C4%29%29
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        want.add(new Point(6, 6));
        want.add(new Point(7, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine17VerticalOverCross() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(0, 10), new Point(10, 0)));
        lines.add(new SegmentExtended(new Point(5,5.1), new Point(5,150)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine18StopStart() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(10, 10), new Point(20, 20)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(10, 10));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine19StopStartVertical() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(5,10), new Point(5,5)));
        lines.add(new SegmentExtended(new Point(5, 5), new Point(5, 0)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine19StopStartAndPoint() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(10, 10), new Point(20, 20)));
        lines.add(new SegmentExtended(new Point(10, 10), new Point(10, 10)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(10, 10));
        want.add(new Point(10, 10));
        want.add(new Point(10, 10));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine20SameStart() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 0)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(0, 0));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine20SameStartAndLaterCollision() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 0)));
        lines.add(new SegmentExtended(new Point(5, 1), new Point(5, 0)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(0, 0));
        want.add(new Point(5, 0));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    
    @Test
    public void testSweepLine18() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(10, 10), new Point(10.1, 10.1)));
        lines.add(new SegmentExtended(new Point(10, 10), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(10,10), new Point(10,10.1)));
        lines.add(new SegmentExtended(new Point(10,10), new Point(10.1,10)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(10, 10));
        want.add(new Point(10, 10));
        want.add(new Point(10, 10));
        want.add(new Point(10, 10));
        want.add(new Point(10, 10));
        want.add(new Point(10, 10));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine19SameLine() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(55.103, 11.578), new Point(55.3611, 11.6755)));
        lines.add(new SegmentExtended(new Point(55.103, 11.578), new Point(55.3611, 11.6755)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(55.103, 11.578));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine20() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(11.592,89.627), new Point(12.2213,88.9493)));
        lines.add(new SegmentExtended(new Point(11.662,89.2365), new Point(11.774, 89.646)));
        lines.add(new SegmentExtended(new Point(43.499, 49.031), new Point(43.5973, 49.499)));
        lines.add(new SegmentExtended(new Point(43.3651, 48.9798), new Point(43.794, 49.185)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(11.728576279579705, 89.47991952221331));
        want.add(new Point(43.50200343199189, 49.045299147224846));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine21() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(5,5), new Point(15,5)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5,5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
   
    @Test
    public void testSweepLine13Overlapping() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,5), new Point(15,15)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine15VeritcalLineOverEndButNotIntersecting() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(6,7), new Point(11,11)));
        lines.add(new SegmentExtended(new Point(6,10), new Point(11,11)));
        lines.add(new SegmentExtended(new Point(11,14), new Point(11,12)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(11, 11));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine16VeritcalLineOverEnd() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(6,7), new Point(11,11)));
        lines.add(new SegmentExtended(new Point(6,10), new Point(11,11)));
        lines.add(new SegmentExtended(new Point(11,14), new Point(11,4)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(11, 11));
        want.add(new Point(11, 11));
        want.add(new Point(11, 11));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine17MultiStartPointIntegrity() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(11,0)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,-10)));
        lines.add(new SegmentExtended(new Point(5,1), new Point(12,1)));
        lines.add(new SegmentExtended(new Point(5,-1), new Point(12,-1)));
        lines.add(new SegmentExtended(new Point(0,-10), new Point(12,10)));//https://www.wolframalpha.com/input/?i=plot%28%280%2C0%29%2810%2C10%29%280%2C0%29%2811%2C0%29%280%2C0%29%2810%2C-10%29%285%2C1%29%2812%2C1%29%285%2C-1%29%2812%2C-1%29+%280%2C-10%29%2812%2C10%29%29
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(3.75, -3.75));
        want.add(new Point(5.4, -1));
        want.add(new Point(6, 0));
        want.add(new Point(6.6000000000000005, 1));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine18StartOnLine() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(5,5), new Point(11,5)));
        lines.add(new SegmentExtended(new Point(3,3), new Point(11,5)));
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(3, 3));
        want.add(new Point(5, 5));
        want.add(new Point(11, 5));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine19StartOnLine() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(5,5), new Point(11,0)));
        lines.add(new SegmentExtended(new Point(5,0), new Point(11,0)));
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        want.add(new Point(11, 0));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine20MultiStartPoints() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,0)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,-10)));
        
        lines.add(new SegmentExtended(new Point(5,10), new Point(10,20)));
        lines.add(new SegmentExtended(new Point(5,10), new Point(10,30)));
        lines.add(new SegmentExtended(new Point(5,10), new Point(10,10))); 
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(5, 10));
        want.add(new Point(5, 10));
        want.add(new Point(5, 10));
        want.add(new Point(10, 10));
        final List<Point> have = finder.findIntersections();  
        assertEquals( want.size(), have.size());
        for(Point h : have)
            assertTrue(want.contains(h));
    }
    
    
    @Test
    public void testSweepLine21MultiStart4Segments() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,0)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,-10)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,30)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        final List<Point> have = finder.findIntersections();  
        assertEquals( want.size(), have.size());
        for(Point h : have)
            assertTrue(want.contains(h));
    }
    
    @Test
    public void testSweepLine22MultiStart() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,0)));
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,-10)));
        
        lines.add(new SegmentExtended(new Point(1,10), new Point(10,20)));
        lines.add(new SegmentExtended(new Point(1,10), new Point(10,10)));
        lines.add(new SegmentExtended(new Point(1,10), new Point(10,0))); //https://www.wolframalpha.com/input/?i=plot%28%280%2C0%29%2810%2C-10%29%280%2C0%29%2810%2C0%29%280%2C0%29%2810%2C10%29%280%2C10%29%2810%2C10%29%280%2C10%29%2810%2C0%29%280%2C10%29%2810%2C20%29%29
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(0, 0));
        want.add(new Point(1, 10));
        want.add(new Point(1, 10));
        want.add(new Point(1, 10));
        want.add(new Point(5.263157894736842, 5.2631578947368425));
        want.add(new Point(10, 10));
        want.add(new Point(10, 0));
        final List<Point> have = finder.findIntersections();
        assertEquals( want.size(), have.size());
        for(Point h : have)
            assertTrue(want.contains(h));
    }
    
    @Test
    public void testSweepLine23IntersectionBehindEnd() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(10,0)));
        
        lines.add(new SegmentExtended(new Point(5,10), new Point(15,0)));
        lines.add(new SegmentExtended(new Point(5,10), new Point(15,10)));
        
        lines.add(new SegmentExtended(new Point(5,-10), new Point(15,0))); 
        lines.add(new SegmentExtended(new Point(5,-10), new Point(15,-10)));  //https://www.wolframalpha.com/input/?i=plot%28%280%2C0%29%2810%2C0%29%285%2C10%29%2815%2C0%29%285%2C10%29%2815%2C10%29%285%2C-10%29%2815%2C0%29%285%2C-10%29%2815%2C-10%29%29
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, -10));
        want.add(new Point(5, 10));
        want.add(new Point(15, 0));
        final List<Point> have = finder.findIntersections(); 
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine24IntersectionBehindEnd() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0,0), new Point(5,0)));
        
        lines.add(new SegmentExtended(new Point(5,10), new Point(15,0)));
        lines.add(new SegmentExtended(new Point(5,10), new Point(15,10)));
        
        lines.add(new SegmentExtended(new Point(5,-10), new Point(15,0))); 
        lines.add(new SegmentExtended(new Point(5,-10), new Point(15,-10)));  //https://www.wolframalpha.com/input/?i=plot%28%280%2C0%29%285%2C0%29%285%2C10%29%2815%2C0%29%285%2C10%29%2815%2C10%29%285%2C-10%29%2815%2C0%29%285%2C-10%29%2815%2C-10%29%29

        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, -10));
        want.add(new Point(5, 10));
        want.add(new Point(15, 0));
        final List<Point> have = finder.findIntersections();  
        assertEquals(want, have);
    }
    
        @Test
    public void testSweepLine14MultiOverlapping() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,5), new Point(15,15)));
        lines.add(new SegmentExtended(new Point(6,6), new Point(15,15)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        want.add(new Point(6, 6));
        want.add(new Point(6, 6));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine18OverlappingIntegrity() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(5,5), new Point(15,15)));
        lines.add(new SegmentExtended(new Point(5,20), new Point(5,3)));
        lines.add(new SegmentExtended(new Point(6,7), new Point(11,11)));
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        want.add(new Point(11, 11));
        final List<Point> have = finder.findIntersections();
        assertEquals(want, have);
    }

    @Test
    public void testSweepLine19_3AtSamePoint() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(0,10), new Point(10,0)));
        lines.add(new SegmentExtended(new Point(0,5), new Point(10,5))); 
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        final List<Point> have = finder.findIntersections();     
        assertEquals(want, have);
    }   
    
    @Test
    public void testSweepLine20_3AtSamePointVertical() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 10)));
        lines.add(new SegmentExtended(new Point(0,10), new Point(10,0)));
        lines.add(new SegmentExtended(new Point(0,5), new Point(10,5))); 
        lines.add(new SegmentExtended(new Point(5,0), new Point(5,10))); 
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        want.add(new Point(5, 5));
        
        final List<Point> have = finder.findIntersections();     
        assertEquals(want, have);
    } 
    
    @Test
    public void testSweepLine21_4AtSamePoint() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 0)));
        lines.add(new SegmentExtended(new Point(0,10), new Point(10,-10)));
        lines.add(new SegmentExtended(new Point(0,5), new Point(10,-5))); 
        lines.add(new SegmentExtended(new Point(0,20), new Point(10,-20))); 
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        final List<Point> have = finder.findIntersections(); 
        assertEquals(want, have);
    }
    
    @Test
    public void testSweepLine22_5AtSamePoint() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 0)));
        lines.add(new SegmentExtended(new Point(0,10), new Point(10,-10)));
        lines.add(new SegmentExtended(new Point(0,5), new Point(10,-5))); 
        lines.add(new SegmentExtended(new Point(0,-5), new Point(10,5))); 
        lines.add(new SegmentExtended(new Point(0,20), new Point(10,-20))); 
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        want.add(new Point(5, 0));
        final List<Point> have = finder.findIntersections(); 
        assertEquals(want, have);
    }
    
//        @Test
//    public void testSweepLine20_3AtSamePointIntegrity() throws Exception {
//        final List<SegmentExtended> lines = new ArrayList<>();
//        lines.add(new SegmentExtended(new Point(0,10), new Point(10,-10)));
//        lines.add(new SegmentExtended(new Point(0,-10), new Point(10,10)));
//        lines.add(new SegmentExtended(new Point(0, 0), new Point(10, 0)));
//        lines.add(new SegmentExtended(new Point(4,-5), new Point(10,-5)));
//        lines.add(new SegmentExtended(new Point(4,5), new Point(10,5)));
//        
//        final BentleyOttmann finder = new BentleyOttmann(lines);
//        
//        final List<Point> want = new ArrayList<>();
//        want.add(new Point(5, 0));
//        want.add(new Point(5, 0));
//        want.add(new Point(5, 0));
//        want.add(new Point(7.5, -5));
//        want.add(new Point(7.5, 5));
//        
//        final List<Point> have = finder.findIntersections(); 
//        System.out.println(want);
//        System.out.println(have);
//        assertEquals(want, have);
//    }
//    
//        @Test
//    public void testSweepLine23_3AtClosePointReal() throws Exception {
//        final List<SegmentExtended> lines = new ArrayList<>();
//        lines.add(new SegmentExtended(new Point(6.473, 78.425), new Point(7.10926, 78.2165)));
//        lines.add(new SegmentExtended(new Point(6.214, 78.777), new Point(6.95541,78.2436)));
//        lines.add(new SegmentExtended(new Point(6.217, 78.844), new Point(7.11801,78.104))); 
//        
//        final BentleyOttmann finder = new BentleyOttmann(lines);
//        
//        final List<Point> want = new ArrayList<>();
//        want.add(new Point(6.895891338825678, 78.28642));
//        want.add(new Point(6.895891338825678, 78.28642));
//        want.add(new Point(6.895891338825678, 78.28642)); //https://www.wolframalpha.com/input/?i=plot%28%286.473%2C+78.425%29%287.10926%2C+78.2165%29%286.214%2C+78.777%29%286.95541%2C78.2436%29%286.217%2C+78.844%29%287.11801%2C78.104%29
//        
//        final List<Point> have = finder.findIntersections();
//        System.out.println(want);
//        System.out.println(have);
//        assertEquals(want.size(), have.size());
//        assertEquals(want.get(0).getX(), have.get(0).getX(),0.01);
//        assertEquals(want.get(0).getY(), have.get(0).getY(),0.01);
//    } 
    
    @Test
    public void testSweepLine23_CloseMulti3IntersectionAnd3NextReal() throws Exception {
        final List<SegmentExtended> lines = new ArrayList<>();
        lines.add(new SegmentExtended(new Point(6.473, 78.425), new Point(7.10926, 78.2165)));
        lines.add(new SegmentExtended(new Point(6.217, 78.844), new Point(7.11801, 78.104)));
        lines.add(new SegmentExtended(new Point(6.874, 78.37), new Point(7.08424, 77.6472)));
        lines.add(new SegmentExtended(new Point(6.214, 78.777), new Point(6.95541, 78.2436))); 
        
        final BentleyOttmann finder = new BentleyOttmann(lines);
        
        final List<Point> want = new ArrayList<>();
        want.add(new Point(6.895891338825627, 78.28642010476041));
        want.add(new Point(6.895891338825627, 78.28642010476041));
        want.add(new Point(6.895891338825627, 78.28642010476041));
        want.add(new Point(6.895950946966192, 78.2863772202806));
        want.add(new Point(6.898951076315598, 78.28421880726354));
        want.add(new Point(6.899067863775552, 78.28381729482035));//https://www.wolframalpha.com/input/?i=plot%28%286.473%2C+78.425%29%287.10926%2C+78.2165%29%286.214%2C+78.777%29%286.95541%2C78.2436%29%286.217%2C+78.844%29%287.11801%2C78.104%29
        
        final List<Point> have = finder.findIntersections();
        assertEquals(want.size(), have.size());
        assertEquals(want.get(0).getX(), have.get(0).getX(),0.00001);
        assertEquals(want.get(0).getY(), have.get(0).getY(),0.00001);
    } 
    
}
