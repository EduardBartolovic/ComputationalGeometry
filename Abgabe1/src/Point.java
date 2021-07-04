package computationalgeometry.Tools;

/**
 *
 * @author Eduard
 */
public class Point implements Comparable<Point>{
    
    private final double x;
    
    private final double y;

    public Point(double x, double y) {
        if(Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("x or y is NaN");
        this.x = x;
        this.y = y;
    }

    public double getX() {return x;}

    public double getY() {return y;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null) 
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Point other = (Point) obj;
        if(!Tool.compareDouble(x, other.x))
            return false;
        return Tool.compareDouble(y, other.y);
//        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x))
//            return false;
//        return Double.doubleToLongBits(this.y) == Double.doubleToLongBits(other.y);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "P{x=" + x + ", y=" + y + '}';
    }
    
    public boolean isOrigin(){
        return x == 0 && y == 0;
    }

    @Override
    public int compareTo(Point o) {
        if(this.getX() < o.getX())
            return -1;
        else if(this.getX() > o.getX())
            return 1;
        else
            if(this.getY() < o.getY())
                return -1;
            else if(this.getY() > o.getY())
                return 1;
            else
                return 0;
    }

    /**
     * Manhatten distance
     * @param other
     * @return 
     */
    public double distance(Point other){
        return Math.abs(x-other.x)+Math.abs(y-other.y);
    }
}
