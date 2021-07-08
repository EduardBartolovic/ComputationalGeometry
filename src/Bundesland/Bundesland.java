package Bundesland;

import computationalgeometry.Tools.Polygon;
import computationalgeometry.Tools.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Eduard
 */
public class Bundesland {
    
    private final List<Polygon> areas;
    
    private Optional<City> captitol;
    
    private final String name;

    public Bundesland(List<Polygon> areas, City captitol, String name) {
        this.areas = areas;
        this.captitol = Optional.of(captitol);
        this.name = name;
    }
    
    public Bundesland(String name) {
        this.areas = new ArrayList<>();
        this.captitol = Optional.empty();
        this.name = name;
    }

    public List<Polygon> getAreas() {
        return areas;
    }

    public Optional<City> getCaptitol() {
        return captitol;
    }

    public String getName() {
        return name;
    }

    public void setCaptitol(City captitol) {
        this.captitol = Optional.of(captitol);
    }

    public void addArea(Polygon area){
        areas.add(area);
    }
    
    public boolean hasHole(){
        if(areas.size() < 2)
            return false;
        for(int m = 0; m < areas.size() ;m++){
            for(int n = 0; n < areas.size(); n++){
                if(m!=n)
                    if(areas.get(m).isPolygonInside(areas.get(n)))
                        return true;
            }
        }
        return false;       
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.areas);
        hash = 89 * hash + Objects.hashCode(this.captitol);
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
        final Bundesland other = (Bundesland) obj;
        if (!Objects.equals(this.areas, other.areas))
            return false;
        return Objects.equals(this.captitol, other.captitol);
    }
    
    public double calculateArea(){
        double sum = 0;
        for(Polygon p : areas){
            //System.out.println("Area: "+p.calculateArea());
            boolean isInside = false;
            for(Polygon p2 : areas){
                if(!p.equals(p2) && p.isPolygonInside(p2) ){ //Check if Hole
                    //System.out.println("true"+p.calculateArea()+"inside of"+p2.calculateArea());
                    isInside = true;
                    break;
                }   
            }
            if(isInside)
                sum -= Math.abs(p.calculateArea());
            else
                sum += Math.abs(p.calculateArea());
        }
        return sum;
    }
    
    /**
     * Polygone müssen vollständige Löcher voneinander sein. Sie dürfen sich nicht schneiden.
     * @param p Punkt
     * @return Ob Punkt in Polygon oder im Loch ist.
     */
    public boolean isPointInside(Point p){
        return areas.stream() 
                .filter(a -> a.isPointInside(p))// all Polygons containing P 
                .count()%2 == 1;
    }

    @Override
    public String toString() {
        return "Bundesland{" + "areas=" + areas.size() + ", captitol=" + captitol + ", name=" + name + ", area=" + calculateArea() +'}';
    }
    
    


    
    
}
