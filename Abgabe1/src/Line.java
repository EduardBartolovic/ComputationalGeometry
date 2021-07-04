package computationalgeometry.Tools;

import java.util.Optional;

/**
 *
 * @author Eduard
 */
public interface Line {
    
    Point getStart();
    
    Point getEnd();
    
    double getLength();
    
    boolean isIntersecting(Line that);
    
    /**
    * calculates the intersection point if present.
    * @param that Line
    * @return Optional<Point> 
    */
    Optional<Point> getIntersection(Line that);
    
    boolean isPoint();
    
    boolean isVertical();
           
    boolean isHorizontal();  
    
    double getMaxX();
    
    double getMaxY();
    
    double getMinX();
    
    double getMinY();
    
    boolean isAEnding(Point p);
    
    boolean isOutOfBoundingBox(Point p);
    
    boolean isOnLine(Point p);
    
    double distanceToPoint(Point o);
   
}
