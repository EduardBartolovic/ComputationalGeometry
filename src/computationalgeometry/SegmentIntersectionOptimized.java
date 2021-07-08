package computationalgeometry;

import SweepLine.BentleyOttmann;
import computationalgeometry.Tools.Point;
import computationalgeometry.Tools.SegmentExtended;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Eduard
 */
public class SegmentIntersectionOptimized {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        System.out.print("s_1000_1.dat: ");
        List<SegmentExtended> list = readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\s_1000_1.dat");
        run(list,0);
        run(list,4);
        run(list,3);
        run(list,2);
        run(list,1);
        
        System.out.print("s_1000_10.dat: ");
        list = readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\s_1000_10.dat");
        run(list,0);
        run(list,4);
        run(list,3);
        run(list,2);
        run(list,1);
        
        System.out.print("s_10000_1.dat: ");
        list = readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\s_10000_1.dat");
        run(list,0);
        run(list,4);
        run(list,3);
        run(list,2);
        run(list,1);
        
        System.out.print("s_100000_1.dat: ");
        list = readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\s_100000_1.dat");
        run(list,0);
        run(list,4);
        run(list,3);
        run(list,2);
        run(list,1);
    }
    
    private static void run(List<SegmentExtended> input, int config){
        switch (config) {
            case 1:
                System.out.print(" NaiveCount:      ");
                break;
            case 2:
                System.out.print(" NaiveList:       ");
                break;
            case 3:
                System.out.print(" ParallelCount:   ");
                break;
            case 4:
                System.out.print(" ParallelList:    ");
                break;
            default:
                System.out.print(" Bentley-Ottmann: ");
                break;
        }
            
        long startTimer = System.currentTimeMillis();
        int counter;
        switch (config) {
            case 1:
                counter = findIntersectionsNaiveCount(input);
                break;
            case 2:
                counter = findIntersectionsNaiveList(input);
                break;
            case 3:
                counter = findIntersectionsParallelCount(input);
                break;
            case 4:
                counter = findIntersectionsParallelList(input);
                break;
            default:
                counter = findIntersections(input);
                break;
        }
        
        long finishTimer = System.currentTimeMillis();
        long timeElapsed = finishTimer - startTimer;
        System.out.print(" Time: "+timeElapsed+" Millisec");
        System.out.println("; Interections found: "+counter);
    }
    
    private static List<SegmentExtended> readList(String path) throws FileNotFoundException{
        final Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
        final List<SegmentExtended> lines = new ArrayList<>();
        while(sc.hasNextLine()) {
            final String[] line = sc.nextLine().trim().split(" ");
            final Point start = new Point(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
            final Point end = new Point(Double.parseDouble(line[2]), Double.parseDouble(line[3]));
            lines.add(new SegmentExtended(start,end));
        }
        System.out.println("FileSize: "+lines.size());
        return lines;
    }
    
    public static int findIntersections(List<SegmentExtended> lines){
        final BentleyOttmann finder = new BentleyOttmann(lines);
        final List<Point> result =  finder.findIntersections();
        //System.out.println("SIZE: " + finder.getSweepLineSize());
        //finder.getSweepLineSize().forEach(System.out::println);
        return result.size();
    }
    
    public static int findIntersectionsNaiveList(List<SegmentExtended> lines){
        
        final int size = lines.size();
        final List<Point> result = new ArrayList<>();
        for(int m = 0 ; m < size-1 ; m++){
            for(int n = m+1 ; n < size; n++){
                if(lines.get(n).isIntersecting(lines.get(m))){
                    result.add(lines.get(n).getIntersection(lines.get(m)).get());
                }
            }
        }
//        System.out.println("No duplicates:" + new HashSet<>(result).size());
//        List<Point> missing = new ArrayList<>(result);
//        missing.removeAll(new HashSet<>(result));
//        System.out.println("Missing:" + missing);
        return result.size();
    }
    
        public static int findIntersectionsNaiveCount(List<SegmentExtended> lines){
        
        final int size = lines.size();
        int counter = 0;
        for(int m = 0 ; m < size-1 ; m++)
            for(int n = m+1 ; n < size; n++)
                if(lines.get(n).isIntersecting(lines.get(m)))
                    counter++;

        return counter;
    }
    
    private static int findIntersectionsParallelCount(List<SegmentExtended> lines){
        final int size = lines.size();
        return Stream.iterate(0,m -> m+1)
                .limit(size-1)
                .parallel()
                .map( m -> {
                    int innerCounter = 0;
                    for(int n = m+1 ; n < size; n++)
                        if(lines.get(n).isIntersecting(lines.get(m)))
                            innerCounter++;
                    return innerCounter;
                })
                .reduce(0,Integer::sum);
    }
    
    private static int findIntersectionsParallelList(List<SegmentExtended> lines){
        final int size = lines.size();
        final List<Point> list = Stream.iterate(0,m -> m+1)
                .limit(size-1)
                .parallel()
                .map( m -> {
                    final List<Point> intersections = new LinkedList<>();
                    for(int n = m+1 ; n < size; n++)
                        if(lines.get(n).isIntersecting(lines.get(m))){
                            intersections.add(lines.get(n).getIntersection(lines.get(m)).get());
//                            if(lines.get(n).getIntersection(lines.get(m)).get().equals(new Point(82.36500059113351, 36.77500358312475)))
//                                System.out.print(lines.get(n)+" "+lines.get(m));
//                            if(lines.get(n).getIntersection(lines.get(m)).get().equals(new Point(6.895903639605895, 78.28641607384114)))
//                                System.out.print(lines.get(n)+" "+lines.get(m));
//                            if(lines.get(n).getIntersection(lines.get(m)).get().equals(new Point(6.89856569837686, 78.28554372723167)))
//                                System.out.print(lines.get(n)+" "+lines.get(m));
//                            if(lines.get(n).getIntersection(lines.get(m)).get().equals(new Point(6.898951076315557, 78.2842188072636)))
//                                System.out.print(lines.get(n)+" "+lines.get(m));
                        }
                    return intersections;
                })
                .flatMap(pointList -> pointList.stream())
                .collect(Collectors.toList());
        
//        final BentleyOttmann finder = new BentleyOttmann(lines);
//        final List<Point> result =  finder.findIntersections();
//        result.removeAll(list);
//        result.forEach( System.out::println);
//        list.stream()
//                .filter(p-> {
//                    boolean contains = false;
//                    for(Point r : result){
//                        if(r.equals(p))
//                            contains = true;
//                    }
//                    return !contains;
//                })
//                .forEach( System.out::println);
//        
        return list.size();
    }
    
//// Compare parralel vs this by plot.....
}
