package computationalgeometry;

import computationalgeometry.Tools.Line;
import computationalgeometry.Tools.Segment;
import computationalgeometry.Tools.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author Eduard
 */
public class SegmentIntersection {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        
        long startTimer = System.currentTimeMillis();
        int counter = findIntersections(readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\s_1000_10.dat"));
        long finishTimer = System.currentTimeMillis();
        long timeElapsed = finishTimer - startTimer;
        System.out.println("Time: "+timeElapsed+" Millisec");
        System.out.println("Interections found: "+counter);
        
        startTimer = System.currentTimeMillis();
        counter = findIntersections(readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\s_1000_1.dat"));
        finishTimer = System.currentTimeMillis();
        timeElapsed = finishTimer - startTimer;
        System.out.println("Time: "+timeElapsed+" Millisec");
        System.out.println("Interections found: "+counter);
        
        startTimer = System.currentTimeMillis();
        counter = findIntersections(readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\s_10000_1.dat"));
        finishTimer = System.currentTimeMillis();
        timeElapsed = finishTimer - startTimer;
        System.out.println("Time: "+timeElapsed+" Millisec");
        System.out.println("Interections found: "+counter);
        
        startTimer = System.currentTimeMillis();
        counter = findIntersections(readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\s_100000_1.dat"));
        finishTimer = System.currentTimeMillis();
        timeElapsed = finishTimer - startTimer;
        System.out.println("Time: "+timeElapsed+" Millisec");
        System.out.println("Interections found: "+counter);
    }
    
    private static List<Line> readList(String path) throws FileNotFoundException{
        final Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
        final List<Line> lines = new ArrayList<>();
        while(sc.hasNextLine()) {
            final String[] line = sc.nextLine().trim().split(" ");
            final Point start = new Point(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
            final Point end = new Point(Double.parseDouble(line[2]), Double.parseDouble(line[3]));
            lines.add(new Segment(start,end));
        }
        System.out.println("FileSize: "+lines.size());
        return lines;
    }
    
    public static int findIntersections(List<Line> lines){
        if(lines.size() > 5000)
            return findIntersectionsParallel(lines);
        final int size = lines.size();
        int counter = 0;
        for(int m = 0 ; m < size-1 ; m++){
            for(int n = m+1 ; n < size; n++){
                if(lines.get(n).isIntersecting(lines.get(m))){
//                    System.out.println("++++++++"+m+"|"+n+"+++");
//                    System.out.println(lines.get(m));
//                    System.out.println(lines.get(n));  
                    counter++;
                }
            }
        }
        return counter;
    }
    
    private static int findIntersectionsParallel(List<Line> lines){
        final int size = lines.size();
        return Stream.iterate(0,m -> m+1)
                .limit(size-1)
                .parallel()
                .map(m-> {
                    int innerCounter = 0;
                    for(int n = m+1 ; n < size; n++){
                        if(lines.get(n).isIntersecting(lines.get(m))){
                            //System.out.println("++++++++"+m+"|"+n+"+++");
                            //System.out.println(lines.get(m));
                            //System.out.println(lines.get(n));  
                            innerCounter++;
                        }
                    }
                    return innerCounter;
                })
                .reduce(0,Integer::sum);
    }
    
    public static List<int[]> findIntersectionsList(List<Segment> lines){
        final int size = lines.size();
        final List<int[]> list = new ArrayList<>();
        for(int m = 0 ; m < size-1 ; m++){
            for(int n = m+1 ; n < size; n++){
                if(lines.get(n).isIntersecting(lines.get(m))){
                    //System.out.println("++++++++"+m+"|"+n+"+++");
                    //System.out.println(lines.get(m));
                    //System.out.println(lines.get(n));
                    list.add(new int[]{m,n});
                }
            }
        }
        return list;
    }
    

}
