package SweepLine;

import static SweepLine.EventType.INTERSECTION;
import computationalgeometry.Tools.SegmentExtended;
import computationalgeometry.Tools.Point;
import java.util.Objects;

public class Event implements Comparable<Event> {

    private final EventType type;
    
    private final Point eventPoint;
    
    private final SegmentExtended[] segments;
    
    private final double value; //x position for sorting purposes

    public Event(EventType type, Point eventPoint, SegmentExtended[] segments) {
      this.type = type; 
      this.eventPoint = eventPoint;
      this.segments = segments;
      this.value = eventPoint.getX();
    }
    
    public Event(EventType type, Point eventPoint, SegmentExtended s) {
      this(type, eventPoint, new SegmentExtended[]{s} );
    }

    public Point getEventPoint() {return this.eventPoint;}

    public SegmentExtended[] getSegments() {return segments;}

    public EventType getType() {return type;}

    public double getValue() {return value;}

    @Override
    public String toString() {return this.type+" " + this.eventPoint;}
    
    @Override
    public int compareTo(Event that) {  
        if (this.value < that.value)
            return -1;
        if (this.value > that.value)
            return 1;
        
        final int typeCompare = this.type.compareTo(that.type); //Order should be START,INTERSECT,VERSICALLINE,END
        if(typeCompare != 0)
            return typeCompare;
        else
            if(this.getEventPoint().getY() < that.getEventPoint().getY())
                return -1;
            else
                return 1;
    }
  
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + Objects.hashCode(this.eventPoint);
        hash = 17 * hash + Objects.hashCode(this.segments);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;       
        final Event that = (Event) o;
        
        if(this.type == INTERSECTION && that.type == INTERSECTION)
            return this.getEventPoint().distance(that.getEventPoint())< 0.00005d ;
        else //if((this.type == INTERSECTION && that.type != INTERSECTION) || (this.type != INTERSECTION && that.type == INTERSECTION))
            return false;
    }

}