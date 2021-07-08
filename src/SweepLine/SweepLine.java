package SweepLine;

import computationalgeometry.Tools.SegmentExtended;
import computationalgeometry.Tools.Tool;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.Consumer;

/**
 *
 * @author Eduard
 */
public class SweepLine {
    
    private final NavigableMap<Double,List<SegmentExtended>> statusTree;
    
    private final List<SegmentExtended> verticalLines;
    
    private double sweepLinePosition;
    
    private class SegmentComparator implements Comparator<Double> {
        @Override
        public int compare(Double s1, Double s2) {
            if(Tool.compareDoubleEasy(s1, s2))
                return 0;
            if(s1 < s2)
                return -1;
            if(s1 > s2)
                return 1;
            return 0;
        }
    }
    
    public SweepLine() {
        this.statusTree = Collections.synchronizedNavigableMap(new TreeMap<>(new SegmentComparator()));
        this.verticalLines = new LinkedList<>();
    }
    
    public void add(SegmentExtended segment){
        if(segment.isVertical())
            verticalLines.add(segment);
        else{
            final double pos = segment.calculateYatX(sweepLinePosition);
            if(!statusTree.containsKey(pos))
                statusTree.put(pos, new LinkedList<>());
            statusTree.get(pos).add(segment);    
        }
    }
    
    public int size(){return statusTree.size()+verticalLines.size();}
    
    public void setSweepLinePosition(double pos){sweepLinePosition = pos;}

    public double getSweepLinePosition() {return sweepLinePosition;}
    
    public List<SegmentExtended> lower(SegmentExtended segment){
        final double pos = segment.calculateYatX(sweepLinePosition);
        if(statusTree.lowerEntry(pos) != null)
            return statusTree.lowerEntry(pos).getValue();
        else
            return null;
    }
    
    public List<SegmentExtended> higher(SegmentExtended segment){
        final double pos = segment.calculateYatX(sweepLinePosition);
        if(statusTree.higherEntry(pos) != null)
            return statusTree.higherEntry(pos).getValue();
        else
            return null;
    }
    
    public void remove(SegmentExtended segment){
        final double pos = segment.calculateYatX(sweepLinePosition);
        if(statusTree.containsKey(pos))
            if(statusTree.get(pos).size() == 1)
                statusTree.remove(pos);
            else
                statusTree.get(pos).remove(segment);
    }
    
    public void removeAllVertical(){
        verticalLines.clear();
    }
    
    public List<SegmentExtended> same(SegmentExtended segment) {
        return statusTree.get(segment.calculateYatX(sweepLinePosition));
    }
    
    public NavigableMap<Double,List<SegmentExtended>> getAllBetween(SegmentExtended segment){
        return statusTree.subMap(segment.getStart().getY(), true, segment.getEnd().getY(), true);
    }
    
    /**
     * TODO: wechsel nur wenn sich Reihenfolge ändert
     * Optimze: Remove imidiatly
     */
    public void tryToReSortAll(){
        final List<Double> toBeReSortedKeys = new ArrayList<>();
        final List<SegmentExtended> toBeReSortedValues = new ArrayList<>();
        statusTree.entrySet()
                .forEach((entry) -> {
                    entry.getValue().forEach((segment) -> {
                        if(segment.calculateYatX(sweepLinePosition) != entry.getKey()) {
                            toBeReSortedKeys.add(entry.getKey());
                            toBeReSortedValues.add(segment);
                        }
            });
        });
        for(int counter = 0 ; counter < toBeReSortedKeys.size(); counter++){
            final SegmentExtended current = toBeReSortedValues.get(counter);
            final double oldKey = toBeReSortedKeys.get(counter);
            final List<SegmentExtended> oldList = statusTree.get(oldKey);
            if(oldList.size() == 1) //delete whole Entry 
                statusTree.remove(oldKey);
            else
                oldList.remove(current);
            add(current);
        }

    }
    
//    public void reSortCloseToIntersection(Double entryKey){
//        final Entry entry =  statusTree.ceilingEntry(entryKey);
//        
//    }
//    
//    public void reSortOneKey(Double key){
//        
//        final List<Segment2DExtended> toBeReSortedValues = new ArrayList<>();
//        
//        final List<Segment2DExtended> segments = statusTree.get(key);
//        if(segments==null)
//            return;
//        segments.forEach( segment -> {
//            if(segment.calculateYatX(sweepLinePosition) != key){
//                toBeReSortedValues.add(segment);
//            }
//        });
//
//        if(segments.size() == toBeReSortedValues.size())
//            statusTree.remove(key);
//        
//        toBeReSortedValues.forEach( segment -> add(segment));
//        return toBeReSortedValues
//    }
    
    public void verticalIntersectionCheck(Consumer<? super SegmentExtended> action){
        verticalLines.forEach(action);
    }

}
