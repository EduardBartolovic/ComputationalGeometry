package computationalgeometry;

import computationalgeometry.Tools.Polygon;
import Bundesland.Bundesland;
import Bundesland.City;
import computationalgeometry.Tools.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Eduard
 */
public class PolygonArea {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        final long startTimer = System.currentTimeMillis();
        final List<Bundesland> bundeslaender = readList("E:\\Documents\\NetBeansProjects\\ComputationalGeometry\\strecken\\DeutschlandMitStaedten.svg");
        
        //bundeslaender.stream().flatMap(b -> b.getAreas().stream()).map(a -> a.isClosed()).forEach(System.out::println);

        bundeslaender.forEach(b -> b.getAreas().forEach(p-> System.out.println(p.getCords().size())));
        bundeslaender.forEach(System.out::println);
        
        //bundeslaender.forEach(b -> System.out.println(b.getName()));
        
        
        //bundeslaender.stream()
        //        .map(b -> b.calculateArea())
        //        .forEach(System.out::println);
        
        System.out.println("Total area of Germany: "+bundeslaender.stream()
                .map(b -> b.calculateArea())
                .reduce(Double::sum).get());
        
        final long finishTimer = System.currentTimeMillis();
        final long timeElapsed = finishTimer - startTimer;
        System.out.println("Time: "+timeElapsed+" Millisec");
    }
    
    private static List<Bundesland> readList(String path) throws FileNotFoundException{
        
        final Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
        final List<Bundesland> bundeslaender = new ArrayList<>();
        
        Bundesland bundesland = null;
        Polygon polygon = null;
        Point lastPoint = null;
        while(sc.hasNextLine()) {
            final String line = sc.nextLine().trim();
            if(line.contains("<path id=")){
                final String[] lineParts = line.trim().split(" ");
                if(bundesland != null){
                    bundeslaender.add(bundesland);
                }
                bundesland = new Bundesland(lineParts[1].substring(4, lineParts[1].length()-1)); //replace last iteration
                
            }else if(line.startsWith("M")){ //new Polygon started 
                polygon = new Polygon(); 
                
                final String[] lineParts = line.substring(1).trim().split(",");
                lastPoint = new Point(Double.parseDouble(lineParts[0]), Double.parseDouble(lineParts[1]));
                polygon.addCord(lastPoint);
                
            }else if(line.startsWith("z")){
                
                bundesland.addArea(polygon);
                
            }else if(line.startsWith("L")){
                
                final String[] lineParts = line.substring(1).trim().split(",");
                final double x = Double.parseDouble(lineParts[0]);
                final double y = Double.parseDouble(lineParts[1]);
                lastPoint = new Point(x,y);
                polygon.addCord(lastPoint);
                
            }else if(line.startsWith("l")){
                final String[] lineParts = line.substring(1).trim().split(",");
                if(lineParts[1].contains("H")){
                    final String[] additional = lineParts[1].split("H");
                    
                    final double x = Double.parseDouble(lineParts[0]);
                    final double y = Double.parseDouble(additional[0]);
                    polygon.addCord(new Point(lastPoint.getX()+x,lastPoint.getY()+y)); //first Part
                    
                    lastPoint = new Point(Double.parseDouble(additional[1]),lastPoint.getY()); //seccond for H119
                    polygon.addCord(lastPoint);
                }else{
                    final double x = Double.parseDouble(lineParts[0]);
                    final double y = Double.parseDouble(lineParts[1]);
                    lastPoint = new Point(lastPoint.getX()+x,lastPoint.getY()+y);
                    polygon.addCord(lastPoint);
                }
                
            }else if(line.equals("</g>")){ //bundesland part ended
                
                bundeslaender.add(bundesland);
                
            }else if(line.startsWith("id=")){ // to handle cities
                
                    final String cityName = line.substring(4, line.length()-1);
                    String nextLine = sc.nextLine().trim();
                    final double x = Double.parseDouble(nextLine.substring(13, nextLine.length()-1));
                    nextLine = sc.nextLine().trim();
                    final double y = Double.parseDouble(nextLine.substring(13, nextLine.length()-1));
                    final City city = new City( cityName, new Point(x, y));
                    final Map<City,Bundesland> map = new HashMap<>();
                    
                    for(Bundesland b : bundeslaender){
                        if(b.isPointInside(city.getLocation())){
                            map.put(city, b);
                        }
                    }
                    
                    map.entrySet() // set captitol
                        .stream()
                        .forEach(entry -> entry.getValue().setCaptitol(entry.getKey()));
                    
            }else{
                //System.out.println(line);
                //throw new IllegalStateException("");
            }
        }
        return bundeslaender;
    }
    
}

