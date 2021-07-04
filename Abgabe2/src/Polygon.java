package computationalgeometry.Tools;

import static computationalgeometry.Tools.Tool.ccw;
import static computationalgeometry.Tools.Tool.orientation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Eduard
 */
public class Polygon {
    
    private final List<Point> cords;

    private Optional<Double> maxX;
    
    public Polygon(List<Point> cords) {
        this.cords = cords;
        maxX = Optional.empty();
    }
    
    public Polygon() {
        this.cords = new ArrayList<>();
        maxX = Optional.empty();
    }

    public List<Point> getCords() {
        return Collections.unmodifiableList(cords);
    }

    public void addCord(Point point){
        cords.add(point);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.cords);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Polygon other = (Polygon) obj;
        return Objects.equals(this.cords, other.cords);
    }
    
    @Override
    public String toString() {
        return "Area{" + "cords=" + cords + '}';
    }
    
    public boolean isClosed(){
        return cords.get(0).equals(cords.get(cords.size()-1));
    }
    
    public boolean isHole(){
        return calculateArea() < 0;
    }

    /**
     * calculate the Area of this Polygon
     * @return double
     */
    public double calculateArea(){
        if(!isClosed())
            throw new IllegalArgumentException("Polygon is not Closed");
                    
        double area = 0;
        final int size = cords.size();
        for(int counter = 1 ; counter < size-1; counter++)
            area += gausstriangle( cords.get(counter), cords.get(counter-1), cords.get(counter+1));
        area += gausstriangle( cords.get(0), cords.get(size-2), cords.get(1)); //to finish a whole round

//        for(int counter = 1 ; counter <= size; counter++){
//            area += gausstriangle( cords.get(counter%size), cords.get((counter-1)%size), cords.get((counter+1)%size));
//        }
        return area/2;
    }

    
    private double gausstriangle(Point a, Point b, Point c){
        return a.getY()*(b.getX()-c.getX());
    }
    
    private double getMaxX(){
        if(maxX.isPresent())
            maxX.get();
        maxX = Optional.of(cords.stream()
                .mapToDouble(c -> c.getX())
                .reduce(Double::max)
                .getAsDouble());
        return maxX.get();
    }
    
    public boolean isPointInside(Point p){
        if(!isClosed())
            throw new IllegalArgumentException("Polygon is not Closed");
        
        //Check if Point lies on an edge
        for(int counter = 1; counter < cords.size() ; counter++)
            if(new Segment(cords.get(counter-1),cords.get(counter)).isOnLine(p))
                return true;          

        final double maxXValue = getMaxX();
        
        if(p.getX() > maxXValue)
            return false;
        
        final Point extreme = new Point(maxXValue+1, p.getY());
        
        int startCounter = 0;
        while(ccw(extreme, p, cords.get(startCounter)) == 0)
            startCounter++;
        
        int intersections = 0;
        double lr = orientation(extreme, p, cords.get(startCounter));
        for(int counter = startCounter+1; counter < cords.size() ; counter++){
            final double lrNew = orientation(extreme, p, cords.get(counter));
            if( Math.abs( lrNew - lr ) == 2){ //detect Sidechange 
                lr = lrNew;
                final double o1 = ccw(cords.get(counter-1), cords.get(counter), extreme);
                final double o2 = ccw(cords.get(counter-1), cords.get(counter), p);
                if(o1*o2<=0) //detect true Sidechanges
                    intersections++;
            }
        }
        for(int counter = 1; counter <= startCounter ; counter++){
            final double lrNew = orientation(extreme, p, cords.get(counter));
            if( Math.abs( lrNew - lr ) == 2){
                lr = lrNew;
                final double o1 = ccw(cords.get(counter-1), cords.get(counter), extreme);
                final double o2 = ccw(cords.get(counter-1), cords.get(counter), p);
                if(o1*o2<=0)
                    intersections++;
            }
        }
        return intersections % 2 == 1; // return true if count is odd, false otherwise
    }
    
    /**
     * Check if This is inside That
     * @param that
     * @return boolean
     */
    public boolean isPolygonInside(Polygon that){
        if( !isClosed() || !that.isClosed() )
            throw new IllegalArgumentException("Polygon is not Closed");
        
        for(int thisCounter = 1; thisCounter < this.cords.size(); thisCounter++){
            final Segment thisLine = new Segment(this.cords.get(thisCounter-1), this.cords.get(thisCounter));
            for(int thatCounter = 1; thatCounter < that.cords.size(); thatCounter++){
                final Segment thatLine = new Segment(that.cords.get(thatCounter-1), that.cords.get(thatCounter));
                if(thisLine.isIntersecting(thatLine))
                    return false; // Is intersecting so this can not be in that
            }
        }
        return that.isPointInside(this.getCords().get(0)); //check if one Point of this is in that   
    }
    
    public boolean hasHole(Set<Polygon> others){
        if(others.size() < 1)
            throw new IllegalArgumentException();
        
        return others.stream()
                .map(poly -> poly.isPolygonInside(this))
                .anyMatch(r -> r);
    }
    
    public Set<Polygon> getHole(Set<Polygon> others){
        if(others.size() < 1)
            throw new IllegalArgumentException();
        
        return others.stream()
                .filter(poly -> poly.isPolygonInside(this))
                .collect(Collectors.toSet());
    }
    
    public Point getMiddle(){
        final double avgX = cords.stream().mapToDouble(p-> p.getX()).sum()/(cords.size()-1);
        final double avgY = cords.stream().mapToDouble(p-> p.getY()).sum()/(cords.size()-1);
        return new Point(avgX, avgY);
    }
    
    public Circle getLargestInscribedCircle(){
        //if(!Konvex) throw new IllegalArgument;
        final Point middle = getMiddle();
        
        double minDistance = Double.POSITIVE_INFINITY;
        for(int counter = 1; counter < cords.size() ; counter++){
            final Segment edge = new Segment(cords.get(counter-1), cords.get(counter));
            final double distance = edge.distanceToPoint(middle);
            if(distance < minDistance)
                minDistance = distance;
        }
        
        return new Circle(middle, minDistance);
    }
    
    public void printLimits(){

        for(int counter = 1; counter < cords.size() ; counter++){
            lineFromPoints(cords.get(counter-1), cords.get(counter));
        }
    }
    
    static void lineFromPoints(Point p, Point q){
        double x1 = p.getX();
        double y1 = p.getY();
        
        double c = (p.getY() - q.getY())/(p.getX() - q.getX());
 
        if(x1==0)
            System.out.println( "y="+y1+"+"+c+"*x");
        else if(c==1){
            System.out.println( "y="+y1+"+"+c*x1+"*x");
        }else
            System.out.println( "y="+y1+"+"+c+"*("+x1+"-x)");

    }
    
    public static void main(String[] args) {
        Polygon p = new Polygon();
        p.addCord(new Point(0, 5));
        p.addCord(new Point(5, 0));
        p.addCord(new Point(10, 5));
        p.addCord(new Point(5, 10));
        p.addCord(new Point(0, 5));
        System.out.println(p);
        p.printLimits();

    }
}
