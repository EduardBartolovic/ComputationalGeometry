package Bundesland;

import computationalgeometry.Tools.Circle;
import computationalgeometry.Tools.Polygon;
import computationalgeometry.Tools.Point;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eduard
 */
public class PolygonTest {
    
    public PolygonTest() {
    }
    
    @Test
    public void testIsComplete() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertTrue( area.isClosed());
    }
    
    @Test
    public void testIsComplete2(){
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(1, 0));
        assertFalse( area.isClosed());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCalculateAreaButIsNotComplete() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(1, 0));
        area.calculateArea();
    }
    
    @Test
    public void testCalculateArea() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertEquals(-100, area.calculateArea(), 0.0);
    }
    
    @Test
    public void testCalculateArea2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        assertEquals(100, area.calculateArea(), 0.0);
    }
    
    @Test
    public void testCalculateArea3() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 0));
        assertEquals(50, area.calculateArea(), 0.0); //triangle
    }
    
    @Test
    public void testCalculateArea4() {
        final Polygon area = new Polygon();
        area.addCord(new Point(10, 10));
        area.addCord(new Point(20, 10));
        area.addCord(new Point(20, 20));
        area.addCord(new Point(10, 10));
        assertEquals(50, area.calculateArea(), 0.0);//triangle
    }
    
    @Test
    public void testIsPointInside() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertTrue( area.isPointInside(new Point(5, 5))); //in Middle
    }
    
    @Test
    public void testIsPointInside2(){
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertTrue( area.isPointInside(new Point(0, 0))); // on leftlower edge
    }
    
    @Test
    public void testIsPointInside3() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertFalse( area.isPointInside(new Point(-1, -1))); //far away
    }
    
    @Test
    public void testIsPointInside4() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertFalse( area.isPointInside(new Point(-1, 5))); // left of square
    }
    
    @Test
    public void testIsPointInside5() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertTrue( area.isPointInside(new Point(0, 5))); // on edge
    }
    
    @Test
    public void testIsPointInside6() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertFalse( area.isPointInside(new Point(-1, 0))); //left of leftlower edge
    }
    
    @Test
    public void testIsPointInside7() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(-1, 1)));//plot((0,5)(4,1)(6,1)(10,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside8() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(-1, -1)));//plot((0,5)(4,1)(6,1)(10,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside9() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(-1, 5)));//plot((0,5)(4,1)(6,1)(10,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside10() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertTrue( area.isPointInside(new Point(1, 5)));//plot((0,5)(4,1)(6,1)(10,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside11() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(11, 5)));//plot((0,5)(4,1)(6,1)(10,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside12() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(2, 8)));//plot((0,5)(4,1)(6,1)(10,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside13() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(8, 8)));//plot((0,5)(4,1)(6,1)(10,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside14() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(10, 10)));//plot((0,5)(4,1)(6,1)(10,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside15() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(2, 2))); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside16() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(0, 0))); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside17() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertTrue( area.isPointInside(new Point(5, 2))); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside18() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(-1, 5))); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside19() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertTrue( area.isPointInside(new Point(1, 5))); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside20() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertTrue( area.isPointInside(new Point(12, 5))); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside21() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        assertFalse( area.isPointInside(new Point(50, 8))); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPointInside22() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(5, 0));
        area.addCord(new Point(0, 0));
        assertFalse( area.isPointInside(new Point(-1, 0)));
    }
    
    @Test
    public void testIsPointInside23() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(5, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertFalse( area.isPointInside(new Point(-1, 0)));
    }
    
    @Test
    public void testIsPointInside24Flat() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertTrue( area.isPointInside(new Point(5, 0)));
    }
    
    @Test
    public void testIsPointInside25() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(2, 2));
        area.addCord(new Point(5, 2));
        area.addCord(new Point(6, 3));
        area.addCord(new Point(7, 2));
        area.addCord(new Point(8, 2));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        assertTrue( area.isPointInside(new Point(1, 2)));//plot((0,0)(2,2)(5, 2)(6, 3)(7, 2)(8, 2)(10, 10)(0, 10)(0, 0))
    }
    
    @Test
    public void testIsPolygonInside() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(4, 4));
        area2.addCord(new Point(5, 4));
        area2.addCord(new Point(5, 5));
        area2.addCord(new Point(4, 5));
        area2.addCord(new Point(4, 4));
        assertFalse( area.isPolygonInside(area2)); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPolygonInside2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(4, 4));
        area2.addCord(new Point(5, 4));
        area2.addCord(new Point(5, 5));
        area2.addCord(new Point(4, 5));
        area2.addCord(new Point(4, 4));
        assertTrue( area2.isPolygonInside(area)); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPolygonInside3() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 5));
        area.addCord(new Point(4, 1));
        area.addCord(new Point(6, 1));
        area.addCord(new Point(10, 5));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(15, 5));
        area.addCord(new Point(5, 10));
        area.addCord(new Point(0, 5));
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(40, 4));
        area2.addCord(new Point(5, 4));
        area2.addCord(new Point(5, 5));
        area2.addCord(new Point(4, 5));
        area2.addCord(new Point(40, 4));
        
        assertFalse( area.isPolygonInside(area2)); //plot((0,5)(4,1)(6,1)(10,5)(10,0)(15,5)(5,10)(0,5))
    }
    
    @Test
    public void testIsPolygonInside4() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(8, 5));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(2, 5));
        area.addCord(new Point(0, 0)); //plot((0,0),(10,0)(8,5)(10,10)(0,10)(2,5)(0,0))
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(1, 1));
        area2.addCord(new Point(9, 1));
        area2.addCord(new Point(9, 9));
        area2.addCord(new Point(1, 9));
        area2.addCord(new Point(1, 1));
        
        assertFalse( area.isPolygonInside(area2));
    }
    
    @Test
    public void testIsPolygonInside5() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(-1, -1));
        area2.addCord(new Point(12, 0));
        area2.addCord(new Point(12, 12));
        area2.addCord(new Point(-1, 12));
        area2.addCord(new Point(-1, 8));
        area2.addCord(new Point(11, 5));
        area2.addCord(new Point(-1, 3));
        area2.addCord(new Point(-1, -1));
        
        assertFalse( area.isPolygonInside(area2));
    }
    
    @Test
    public void testIsHole() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        assertTrue( area.isHole());
    }
    
    @Test
    public void testIsHole2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        assertFalse( area.isHole());
    }
    
    
    @Test
    public void testHasHole() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(1, 1));
        area2.addCord(new Point(9, 1));
        area2.addCord(new Point(9, 9));
        area2.addCord(new Point(1, 9));
        area2.addCord(new Point(1, 1));
        
        final Set<Polygon> set = new HashSet<>();
        set.add(area2);
        assertTrue( area.hasHole(set));
    }  
    
    @Test
    public void testHasHole2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        final Polygon area2 = new Polygon();
        area2.addCord(new Point(0, 0));
        area2.addCord(new Point(10, 0));
        area2.addCord(new Point(10, 10));
        area2.addCord(new Point(0, 10));
        area2.addCord(new Point(0, 0));
        
        final Set<Polygon> set = new HashSet<>();
        set.add(area2);
        assertFalse( area.hasHole(set));
    }
    
    @Test
    public void testGetMiddle() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        assertEquals(new Point(5, 5), area.getMiddle());
    }
    
    @Test
    public void testGetCircle() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 0));
        
        assertEquals(new Circle(new Point(5, 5), 5), area.getLargestInscribedCircle());
    }
    
    @Test
    public void testGetCircle2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        assertEquals(new Circle(new Point(5, 5), 5), area.getLargestInscribedCircle());
    }
    
    @Test
    public void testGetCircle3() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 20));
        area.addCord(new Point(10, 20));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        assertEquals(new Circle(new Point(5, 10), 5), area.getLargestInscribedCircle());
    }
    
    @Test
    public void testGetCircle4ExtendedLine() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(0, 20));
        area.addCord(new Point(10, 20));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        assertEquals(new Circle(new Point(5, 10), 5), area.getLargestInscribedCircle());
    }
    
    
    @Test
    public void testGetCircle5House() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(5, 15));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        assertEquals(new Circle(new Point(5, 7), 5), area.getLargestInscribedCircle());
    }
    
    @Test
    public void testGetCircle6House2() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(5, 150));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        //assertEquals(new Circle(new Point(5, 7), 5), area.getLargestInscribedCircle());
    }
    
    @Test
    public void testGetCircle7House3() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 10));
        area.addCord(new Point(5, 40));
        area.addCord(new Point(10, 10));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        assertEquals(new Circle(new Point(5, 5), 5), area.getLargestInscribedCircle());
    }
    
    @Test
    public void testGetCircle8ExtendedLine() {
        final Polygon area = new Polygon();
        area.addCord(new Point(0, 0));
        area.addCord(new Point(0, 13));
        area.addCord(new Point(0, 20));
        area.addCord(new Point(10, 20));
        area.addCord(new Point(10, 13));
        area.addCord(new Point(10, 0));
        area.addCord(new Point(0, 0));
        
        assertEquals(new Circle(new Point(5, 11), 5), area.getLargestInscribedCircle());
    }
    
    @Test
    public void testGetCircle6Big() {
        final Polygon area = new Polygon();
        area.addCord(new Point(59.9798 , 310.1933));
        area.addCord(new Point(15.6250 , 491.2127));
        area.addCord(new Point(16.6331 , 515.8172));
        area.addCord(new Point(29.7379 , 565.0264));
        area.addCord(new Point(66.0282 , 654.6573));
        area.addCord(new Point(87.1976 , 688.0492));
        area.addCord(new Point(177.9234 , 830.4042));
        area.addCord(new Point(233.3669 , 895.4306));
        area.addCord(new Point(248.4879 , 905.9754));
        area.addCord(new Point(320.0605 , 942.8822));
        area.addCord(new Point(405.7460 , 974.5167));
        area.addCord(new Point(521.6734 , 993.8489));
        area.addCord(new Point(618.4476 , 949.9121));
        area.addCord(new Point(660.7863 , 927.0650));
        area.addCord(new Point(681.9556 , 913.0053));
        area.addCord(new Point(866.4315 , 688.0492));
        area.addCord(new Point(929.9395 , 591.3884));
        area.addCord(new Point(929.9395 , 498.2425));
        area.addCord(new Point(912.8024 , 392.7944));
        area.addCord(new Point(887.6008 , 311.9508));
        area.addCord(new Point(832.1573 , 192.4429));
        area.addCord(new Point(732.3589 ,  72.9350));
        area.addCord(new Point(690.0202 ,  50.0879));
        area.addCord(new Point(630.5444 ,  21.9684));
        area.addCord(new Point(597.2782 ,  11.4236));
        area.addCord(new Point(524.6976 ,  16.6960));
        area.addCord(new Point(482.3589 ,  21.9684));
        area.addCord(new Point(456.1492 ,  27.2408));
        area.addCord(new Point(424.8992 ,  34.2707));
        area.addCord(new Point(346.2702 ,  55.3603));
        area.addCord(new Point(295.8669 ,  74.6924));
        area.addCord(new Point(239.4153 ,  97.5395));
        area.addCord(new Point(179.9395 , 127.4165));
        area.addCord(new Point(128.5282 , 167.8383));
        area.addCord(new Point(103.3266 , 206.5026));
        area.addCord(new Point(85.1815 , 243.4095));
        area.addCord(new Point(67.0363 , 283.8313));
        area.addCord(new Point(59.9798 , 310.1933));

        
        assertEquals(new Circle(new Point(420.1721918918919, 433.9524108108108), 379.296553542444), area.getLargestInscribedCircle());
    }
    
}
