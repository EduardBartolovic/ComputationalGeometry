package Bundesland;

import computationalgeometry.Tools.Polygon;
import computationalgeometry.Tools.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduard
 */
public class BundeslandTest {
    
    public BundeslandTest() {
    }
    
    @Test
    public void testIsPointInside() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        assertTrue( bundesland.isPointInside(new Point(5, 5)));
    }
    
    @Test
    public void testIsPointInside2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        assertFalse( bundesland.isPointInside(new Point(15, 5)));
    }
    
    @Test
    public void testIsPointInside3() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(11, 0));
        area2.addCord(new Point(21, 0));
        area2.addCord(new Point(21, 10));
        area2.addCord(new Point(11, 10));
        area2.addCord(new Point(11, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        bundesland.addArea(area2);
        assertTrue( bundesland.isPointInside(new Point(15, 5)));
    }
    
    @Test
    public void testIsPointInside4() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(11, 0));
        area2.addCord(new Point(21, 0));
        area2.addCord(new Point(21, 10));
        area2.addCord(new Point(11, 10));
        area2.addCord(new Point(11, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        bundesland.addArea(area2);
        assertFalse( bundesland.isPointInside(new Point(10.5, 5)));
    }
    
    @Test
    public void testIsPointInside5Hole() {
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(3, 3));
        area2.addCord(new Point(3, 7));
        area2.addCord(new Point(7, 7));
        area2.addCord(new Point(7, 3));
        area2.addCord(new Point(3, 3));
        
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        bundesland.addArea(area2);
        assertFalse( bundesland.isPointInside(new Point(5, 5))); // is inside Hole
    }
    
    @Test
    public void testIsPointInside6DoubleHole() {
        final Polygon area3 = new Polygon();
        area3.addCord(new Point(4, 4));
        area3.addCord(new Point(4, 6));
        area3.addCord(new Point(6, 6));
        area3.addCord(new Point(6, 4));
        area3.addCord(new Point(4, 4));
        
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(3, 3));
        area2.addCord(new Point(3, 7));
        area2.addCord(new Point(7, 7));
        area2.addCord(new Point(7, 3));
        area2.addCord(new Point(3, 3));
        
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        bundesland.addArea(area2);
        bundesland.addArea(area3);
        assertTrue( bundesland.isPointInside(new Point(5, 5))); // is inside Hole
    }
    
    @Test
    public void testHasHole() {  
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(3, 3));
        area2.addCord(new Point(3, 7));
        area2.addCord(new Point(7, 7));
        area2.addCord(new Point(7, 3));
        area2.addCord(new Point(3, 3));
        
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        bundesland.addArea(area2);
        assertTrue( bundesland.hasHole());
    }
    
    @Test
    public void testHasHole2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        assertFalse( bundesland.hasHole());
    }
    
    @Test
    public void testHasHole3() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        assertFalse( bundesland.hasHole());
    }
    
    @Test
    public void testCalculateArea() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        assertEquals(100, bundesland.calculateArea(),0.0);
    }
    
    @Test
    public void testCalculateArea2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(11, 0));
        area2.addCord(new Point(21, 0));
        area2.addCord(new Point(21, 10));
        area2.addCord(new Point(11, 10));
        area2.addCord(new Point(11, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        bundesland.addArea(area2);
        assertEquals(200, bundesland.calculateArea(),0.0);
    }
    
    @Test
    public void testCalculateArea3() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(1, 1));
        area2.addCord(new Point(1, 9));
        area2.addCord(new Point(9, 9));
        area2.addCord(new Point(9, 1));
        area2.addCord(new Point(1, 1));
        
        final Polygon area3 = new Polygon();
        area3.addCord(new Point(11, 0));
        area3.addCord(new Point(21, 0));
        area3.addCord(new Point(21, 10));
        area3.addCord(new Point(11, 10));
        area3.addCord(new Point(11, 0));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area);
        bundesland.addArea(area2);
        bundesland.addArea(area3);
        assertEquals(136, bundesland.calculateArea(),0.0);
    }
    
    @Test
    public void testCalculateArea4() {
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(1, 1));
        area2.addCord(new Point(1, 9));
        area2.addCord(new Point(9, 9));
        area2.addCord(new Point(9, 1));
        area2.addCord(new Point(1, 1));
        
        final Bundesland bundesland = new Bundesland("Testland");
        bundesland.addArea(area2);
        assertEquals(64, bundesland.calculateArea(),0.0);
    }
    
    
}
