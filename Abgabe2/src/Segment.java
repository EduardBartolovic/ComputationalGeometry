package computationalgeometry.Tools;

import static computationalgeometry.Tools.Tool.doubleCorrection;
import static computationalgeometry.Tools.Tool.onSegment;
import static computationalgeometry.Tools.Tool.orientation;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Segment
 * @author Eduard
 */
public class Segment implements Line{
    
    /** Start Point */
    private final Point start;
    
    /** End Point */
    private final Point end;

    /**
     * Consructor
     * @param start Point
     * @param end Point
     */
    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Point getStart() {return start;}

    @Override
    public Point getEnd() {return end;}
    
    @Override
    public double getLength() {
        return Math.sqrt(Math.pow(end.getX()-start.getX(),2) + Math.pow(end.getX()-start.getX(),2));
    }

    @Override
    public String toString() {
        return "Line{s=" + start + ", e=" + end + '}';
    }
    
        @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Segment other = (Segment) obj;
        if (!Objects.equals(this.start, other.start))//Maybe extend it to Orientation???????
            return false;
        return Objects.equals(this.end, other.end);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.start);
        hash = 59 * hash + Objects.hashCode(this.end);
        return hash;
    }
    
    @Override
    public boolean isIntersecting(Line that){
        final Point start1 = this.start;
        final Point end1 = this.end;
        final Point start2 = that.getStart();
        final Point end2 = that.getEnd();
        
        final int o1 = orientation(start1, end1, start2);
        final int o2 = orientation(start1, end1, end2);
        final int o3 = orientation(start2, end2, start1);
        final int o4 = orientation(start2, end2, end1);

        if(o1 != o2 && o3 != o4)// General Interesction case
            return true;
        
        if(o1 == 0 && o2 == 0 && o3 == 0 && o4 == 0)// If the segments are colinear -> check for overlap
            return onSegment(start1, start2, end1) || onSegment(start1, end2, end1) || onSegment(start2, start1, end2) || onSegment(start2, end1, end2);
        
        return false; // Doesn't fall in any in general or special cases -> No Intersection
    }
    
    @Override
    public Optional<Point> getIntersection(Line that) {
        
        final double x1 = this.getStart().getX();
        final double y1 = this.getStart().getY();
        final double x2 = this.getEnd().getX();
        final double y2 = this.getEnd().getY();
        final double x3 = that.getStart().getX();
        final double y3 = that.getStart().getY();
        final double x4 = that.getEnd().getX();
        final double y4 = that.getEnd().getY();
        
        if(this.isPoint() && that.isOnLine(this.getStart()))
            return Optional.of(start);
        if(that.isPoint() && this.isOnLine(that.getStart()))
            return Optional.of(that.getStart());
        
        final int o1 = orientation(this.getStart(), this.getEnd(), that.getStart());
        final int o2 = orientation(this.getStart(), this.getEnd(), that.getEnd());
        final int o3 = orientation(that.getStart(), that.getEnd(), this.getStart());
        final int o4 = orientation(that.getStart(), that.getEnd(), this.getEnd());
        if(o1 == 0 && o2 == 0 && o3 == 0 && o4 == 0)// If the segments are colinear -> check for overlap
            return getOverlapPoint(that);
        
        final double r = (x2 - x1) * (y4 - y3) - (y2 - y1) * (x4 - x3);
        if(r != 0) {
            final double t = ((x3 - x1) * (y4 - y3) - (y3 - y1) * (x4 - x3)) / r;
            final double u = ((x3 - x1) * (y2 - y1) - (y3 - y1) * (x2 - x1)) / r;
            if(t >= 0 && t <= 1 && u >= 0 && u <= 1) {
                final double x_c;
                if(that.isVertical())
                    x_c = that.getStart().getX();
                else if(this.isVertical())
                    x_c = this.getStart().getX();
                else
                    x_c = x1 + t * (x2 - x1);
                
                final double y_c;
                if(that.isHorizontal())
                    y_c = that.getStart().getY();
                else if(this.isHorizontal())
                    y_c = this.getStart().getY();
                else
                    y_c = y1 + t * (y2 - y1);
                
                final double x = doubleCorrection(x_c);
                final double y = doubleCorrection(y_c);
                return Optional.of(new Point(x, y));
            }
        }
        return Optional.empty();
    }
    
    public Optional<Point> getOverlapPoint(Line that){
        final Point startThis = this.getStart();
        final Point endThis = this.getEnd(); 
        final Point startThat = that.getStart();
        final Point endThat = that.getEnd();
        
        final double slope = (endThis.getY() - startThis.getY())/(endThis.getX() - startThis.getX());
        final boolean isHorizontal = this.isHorizontal();
        final boolean isDescending = slope < 0 && !isHorizontal;
        final int invertY = isDescending || isHorizontal ? -1 : 1;

        final Point min1 = new Point(Math.min(startThis.getX(), endThis.getX()), Math.min(startThis.getY()*invertY, endThis.getY()*invertY));
        final Point max1 = new Point(Math.max(startThis.getX(), endThis.getX()), Math.max(startThis.getY()*invertY, endThis.getY()*invertY));

        final Point min2 = new Point(Math.min(startThat.getX(), endThat.getX()), Math.min(startThat.getY()*invertY, endThat.getY()*invertY));
        final Point max2 = new Point(Math.max(startThat.getX(), endThat.getX()), Math.max(startThat.getY()*invertY, endThat.getY()*invertY));

        final Point minIntersection;
        if (isDescending)
            minIntersection = new Point(Math.max(min1.getX(), min2.getX()), Math.min(min1.getY()*invertY, min2.getY()*invertY));
        else
            minIntersection = new Point(Math.max(min1.getX(), min2.getX()), Math.max(min1.getY()*invertY, min2.getY()*invertY));

        final Point maxIntersection;
        if (isDescending)
            maxIntersection = new Point(Math.min(max1.getX(), max2.getX()), Math.max(max1.getY()*invertY, max2.getY()*invertY));
        else
            maxIntersection = new Point(Math.min(max1.getX(), max2.getX()), Math.min(max1.getY()*invertY, max2.getY()*invertY));

       final boolean intersect = minIntersection.getX() <= maxIntersection.getX() && 
                         (!isDescending && minIntersection.getY() <= maxIntersection.getY() ||
                           isDescending && minIntersection.getY() >= maxIntersection.getY());

        if(!intersect)
            return Optional.empty();

        return Optional.of(minIntersection);
    }
    
    @Override
    public boolean isVertical(){return start.getX()==end.getX();}
    
    @Override
    public boolean isHorizontal(){return start.getY()==end.getY();}
    
    @Override
    public boolean isPoint(){ return start.getX()==end.getX() && start.getY()==end.getY();}
    
    @Override
    public double getMaxX(){return Math.max(start.getX(),end.getX());}
    
    @Override
    public double getMinX(){return Math.min(start.getX(),end.getX());}
    
    @Override
    public double getMaxY(){return Math.max(start.getY(),end.getY());}
    
    @Override
    public double getMinY(){return Math.min(start.getY(),end.getY());}
    
    @Override
    public boolean isOnLine(Point p) {
        if(p.getX() > getMaxX() || p.getX() < getMinX() || p.getY() > getMaxY() || p.getY() < getMinY())// check Bounding Boxes
            return false;
        return Tool.ccw(start, end , p) == 0; //Check if kollinear
    }
    
    @Override
    public boolean isAEnding(Point p){return start.equals(p) || end.equals(p);}
    
    /*
     * checks if point is within segment range.
     * @param Point p
     * @return boolean
     */
    @Override
    public boolean isOutOfBoundingBox(Point p){
        return (p.getX() < start.getX() && p.getX() < end.getX()) || (p.getX() > start.getX() && p.getX() > end.getX())
                || (p.getY() < start.getY() && p.getY() < end.getY()) || (p.getY() > start.getY() && p.getY() > end.getY());
    }
    
    @Override
    public double distanceToPoint(Point o){
        final double x0 = o.getX();
        final double y0 = o.getY();
        final double x1 = start.getX();
        final double y1 = start.getY();
        final double x2 = end.getX();
        final double y2 = end.getY();
        return Math.abs( (x2-x1)*(y1-y0) - (x1-x0)*(y2-y1) ) / Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) );
    }
    
}