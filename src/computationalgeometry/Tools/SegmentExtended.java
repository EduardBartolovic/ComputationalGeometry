package computationalgeometry.Tools;

import java.util.Objects;

/**
 *
 * @author Eduard
 */
public final class SegmentExtended extends Segment{
    
    private double value;

    public SegmentExtended(Point a, Point b) {
        super(a,b);
        value = getStart().getY();
    }
    
    @Override
    public Point getStart() {
        final Point a = super.getStart();
        final Point b = super.getEnd();
        
        if(a.getX() < b.getX())
            return a;
        else if(a.getX() > b.getX())
            return b;
        else
            if(a.getY() < b.getY())
                return a;
            else
                return b;
    }

    @Override
    public Point getEnd() {
        final Point a = super.getStart();
        final Point b = super.getEnd();
        
        if(a.getX() < b.getX())
            return b;
        else if(a.getX() > b.getX())
            return a;
        else
            if(a.getY() < b.getY())
                return b;
            else
                return a;
    }
    
    public double getValue(){return value;}
    
    public void setValue(double value){this.value = value;}

    public double calculateYatX(double x){
        value = getStart().getY() + ((getEnd().getY() - getStart().getY()) / (getEnd().getX() - getStart().getX())) * (x - getStart().getX());
        return value;
    }
    
    @Override
    public String toString() {return "Line{s=" + getStart() + ", e=" + getEnd() + ", value="+ getValue() +'}';}

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final SegmentExtended other = (SegmentExtended) obj;
        if (Double.doubleToLongBits(this.value) != Double.doubleToLongBits(other.value))
            return false;
        if (!Objects.equals(this.getStart(), other.getStart())) 
            return false;
        return Objects.equals(this.getEnd(), other.getEnd());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.getStart());
        hash = 59 * hash + Objects.hashCode(this.getEnd());
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }
    
    @Override
    public double getMaxX(){return getEnd().getX();} //end is already right
    
    @Override
    public double getMinX(){return getStart().getX();} //start is already left
    
}