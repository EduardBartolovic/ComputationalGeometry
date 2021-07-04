package computationalgeometry.Tools;

/**
 *
 * @author Eduard
 */
public class Tool {
    
    public static final double EPSILON = 0.0000000001d;
    public static final double BIGGEREPSILON = 0.00003d;
    
    public static boolean compareDouble(double a, double b){
        return Math.abs(a - b) < EPSILON;
    }
    
    public static boolean compareDoubleEasy(double a, double b){
        return Math.abs(a - b) < BIGGEREPSILON;
    }
    
    public static double doubleCorrection(double a){
        if( Math.abs(a - (int) a ) < EPSILON )
            return (int) a;
        else
            return a;
    }
    
    /**
     * Find orientation of p, q, r.
     * @param p Point
     * @param q Point
     * @param r Point
     * @return 0 -> p, q and r are colinear, 1 -> Clockwise, 2 -> Counterclockwise
     */
    public static int orientation(Point p, Point q, Point r){
        final double ccw = ccw( p, q, r);
        
        if(Tool.compareDouble(ccw, 0))
            return 0; // colinear
        else if(ccw > 0)
            return 1; //clockwise
        else
            return -1; //counterclock
    }
    
    /**
     * Calculate CCW
     * @param p
     * @param q
     * @param r
     * @return 
     */
    public static double ccw(Point p, Point q, Point r){   
        return p.getX()*q.getY() - p.getY()*q.getX() + q.getX()*r.getY() - q.getY()*r.getX() + p.getY()*r.getX() - p.getX()*r.getY();
    }
    
    public static double cross(Point o, Point a, Point b){
        return (a.getX() - o.getX()) * (b.getY() - o.getY()) - (a.getY() - o.getY()) * (b.getX() - o.getX());
    }
        
    
    /**
     * Given three colinear points p, r, q, the function checks if point r lies on line segment 'pq'
     * @param p
     * @param r
     * @param q
     * @return boolean
     */
    public static boolean onSegment(Point p, Point r, Point q){
        return r.getX() <= Math.max(p.getX(), q.getX()) &&
                r.getX() >= Math.min(p.getX(), q.getX()) &&
                r.getY() <= Math.max(p.getY(), q.getY()) &&
                r.getY() >= Math.min(p.getY(), q.getY());
    }

}
