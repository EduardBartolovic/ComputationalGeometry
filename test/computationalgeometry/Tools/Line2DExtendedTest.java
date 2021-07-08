
package computationalgeometry.Tools;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduard
 */
public class Line2DExtendedTest {
    
    public Line2DExtendedTest() {
    }
    
    @Test
    public void testGetStart() throws Exception {
        Segment line2D = new SegmentExtended(new Point(0, 0), new Point(10, 10));
        assertEquals(new Point(0, 0), line2D.getStart());
    }
    
    @Test
    public void testGetEnd() throws Exception {
        Segment line2D = new SegmentExtended(new Point(0, 0), new Point(10, 10));
        assertEquals(new Point(10, 10), line2D.getEnd());
    }
    
    @Test
    public void testGetStart2() throws Exception {
        Segment line2D = new SegmentExtended(new Point(10, 10), new Point(0, 0));
        assertEquals(new Point(0, 0), line2D.getStart());
    }
    
    @Test
    public void testGetEnd2() throws Exception {
        Segment line2D = new SegmentExtended(new Point(10, 10), new Point(0, 0));
        assertEquals(new Point(10, 10), line2D.getEnd());
    }
    
}
