package SweepLine;

import static SweepLine.EventType.END;
import static SweepLine.EventType.INTERSECTION;
import static SweepLine.EventType.START;
import static SweepLine.EventType.VERTICALLINE;
import computationalgeometry.Tools.SegmentExtended;
import computationalgeometry.Tools.Point;
import computationalgeometry.Tools.Tool;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.PriorityBlockingQueue;

public class BentleyOttmann {

    private final PriorityBlockingQueue<Event> eventQueue; //contains Events
    
    //private final List<Integer> sweepLineSize;
    
    private final SweepLine sweepLine;
    
    private final List<Point> intersections; //contains intersections found -> may just use counter
    
    private boolean doesVerticalLineExist;
    
    public BentleyOttmann(List<SegmentExtended> inputData) {
        eventQueue = new PriorityBlockingQueue<>(inputData.size()*2); // line segments that cross the sweep line L, ordered by the y-coordinates of the crossing points.
        sweepLine = new SweepLine();
        intersections = new LinkedList<>(); //List of all found intersections
        inputData.forEach((line) -> {
            if(line.isVertical())
                eventQueue.add(new Event(VERTICALLINE, line.getStart(), line));//add vertical line
            else{
                eventQueue.add(new Event(START, line.getStart(), line));//add left point
                eventQueue.add(new Event(END, line.getEnd(), line));//add right point
            }
        });
        //sweepLineSize = new LinkedList<>();
    }
    
    public List<Point> findIntersections() {
        while(!eventQueue.isEmpty()){
            //sweepLineSize.add(sweepLine.size());
            final Event currentEvent = eventQueue.poll(); //get Next Event
            //System.out.println("Event: "+currentEvent+"Size: Q: "+eventQueue.size()+" T: "+sweepLine.size());
            handle(currentEvent);
        }
        return intersections;
    }

//    public List<Integer> getSweepLineSize() {
//        return sweepLineSize;
//    }

    private void handle(Event currentEvent){
        
        final double newSweepLinePosition = currentEvent.getValue();
        if(sweepLine.getSweepLinePosition() < newSweepLinePosition){
            
            if(doesVerticalLineExist){//Remove All old Vertical Lines if sweepLine moves
                sweepLine.removeAllVertical();
                doesVerticalLineExist = false;
            }
            sweepLine.setSweepLinePosition(newSweepLinePosition);//Move SweepLine to new Position
//            if(currentEvent.getType() != INTERSECTION)
                sweepLine.tryToReSortAll();
//            else{
//                
//            }
        }
        
        switch(currentEvent.getType()){
            case START:
                handleStartEvent(currentEvent);
                break; //End START case
            case END:
                handleEndEvent(currentEvent);
                break; //End END case
            case INTERSECTION:
                handleIntersectionEvent(currentEvent);
                break; //End INTERSECTION case
            case VERTICALLINE:
                handleVerticalLineEvent(currentEvent);
                break; //End INTERSECTION case
          }
    }

    private void handleStartEvent(Event currentEvent){
        
        final SegmentExtended currentSegment = currentEvent.getSegments()[0];
        sweepLine.add(currentSegment); // Add new Segment to sweepLine
        
        final List<SegmentExtended> lower = sweepLine.lower(currentSegment);
        if(lower != null)
            for(SegmentExtended l : lower)
                checkForIntersection(l, currentSegment);
        
        final List<SegmentExtended> higher = sweepLine.higher(currentSegment);
        if(higher != null) 
            for(SegmentExtended h : higher)
                checkForIntersection(h , currentSegment);
        
        final int size = sweepLine.same(currentSegment).size();
        for(int counter = 1; counter < size ;counter++)// Do one less because size always atleast 1
            intersections.add(currentEvent.getEventPoint());      
        
    }
    
    private void handleEndEvent(Event currentEvent){
        
        final SegmentExtended currentSegment = currentEvent.getSegments()[0];
        final List<SegmentExtended> lower = sweepLine.lower(currentSegment);
        if(lower != null){
            final List<SegmentExtended> higher = sweepLine.higher(currentSegment);
            if(higher != null )
                for(SegmentExtended l : lower)
                    for(SegmentExtended h : higher)
                        checkForIntersection(l , h);
        }
        sweepLine.remove(currentSegment); //Delete current segment from SweeplineTree
    }
    
private void handleIntersectionEvent(Event currentEvent){
        
        final SegmentExtended intersectionSegment1 = currentEvent.getSegments()[0]; 
        
        final List<SegmentExtended> same = sweepLine.same(intersectionSegment1);
        final int sameSize = same.size();
        
        SegmentExtended highestIntersection = null;
        SegmentExtended lowestIntersection = null;
        
        if(sameSize > 2){
            for(int counter = 0; counter < (sameSize*sameSize-sameSize)/2 ; counter++)// (n^2-n)/2 
                intersections.add(currentEvent.getEventPoint());
            
            double minY = Double.POSITIVE_INFINITY;
            double maxY = Double.NEGATIVE_INFINITY; 
            for(SegmentExtended currentSegment: same){
                final double y = currentSegment.calculateYatX(sweepLine.getSweepLinePosition()+Tool.EPSILON);
                if(y < minY){
                    minY = y;
                    highestIntersection = currentSegment;
                }
                if(y > maxY){
                    maxY = y;
                    lowestIntersection = currentSegment;
                }
            }
            
        }else{
            intersections.add(currentEvent.getEventPoint());
            
            final SegmentExtended intersectionSegment2 = currentEvent.getSegments()[1];
            final double ys1 = intersectionSegment1.calculateYatX(sweepLine.getSweepLinePosition()+Tool.EPSILON);
            final double ys2 = intersectionSegment2.calculateYatX(sweepLine.getSweepLinePosition()+Tool.EPSILON);
            if(ys1 > ys2) {
                highestIntersection = intersectionSegment1;
                lowestIntersection = intersectionSegment2;
            }else{
                highestIntersection = intersectionSegment2;
                lowestIntersection = intersectionSegment1;
            }
        }
         
        final List<SegmentExtended> higher = sweepLine.higher(highestIntersection);
        if(higher != null)
            for(SegmentExtended h : higher)
                checkForIntersection(h, highestIntersection);

        final List<SegmentExtended> lower = sweepLine.lower(lowestIntersection);
        if(lower != null)
            for(SegmentExtended l : lower)
                checkForIntersection(l , lowestIntersection);

    }

    /**
     * Handle a Vertical Event 
     * @param currentEvent 
     */
    private void handleVerticalLineEvent(Event currentEvent){
        final SegmentExtended currentSegment = currentEvent.getSegments()[0];
        sweepLine.getAllBetween(currentSegment).values().forEach((values) -> values.forEach(line -> checkForIntersection(line, currentSegment)));
        sweepLine.verticalIntersectionCheck(line -> checkForIntersection(line, currentSegment));
        sweepLine.add(currentSegment);
        doesVerticalLineExist = true;
    }
    
    /**
     * Find any intersections between s1, s2 and add it to eventQueue
     * @param s1 Segment
     * @param s2 Segment
     * @return true if found an intersection or false otherwise
     */
    private void checkForIntersection( SegmentExtended s2, SegmentExtended s1) {
        final Optional<Point> possibleIntersection = s1.getIntersection(s2);
        if(possibleIntersection.isPresent()){
            final Point intersection = possibleIntersection.get();
            if(intersection.getX() > sweepLine.getSweepLinePosition()){ //only add new intersections that are after the current sweepLine
                final Event event = new Event(INTERSECTION, intersection, new SegmentExtended[]{s1, s2});
                if(!eventQueue.contains(event))
                    eventQueue.add(event);
            }else if(intersection.getX() == sweepLine.getSweepLinePosition())
                intersections.add(intersection);
        }
    }
    
}