package computationalgeometry.Tools;

import java.util.Objects;

/**
 *
 * @author Eduard
 */
public class Circle {
    
    
    private final Point middle;
    
    private final double radius;

    public Circle(Point middle, double radius) {
        this.middle = middle;
        this.radius = radius;
    }

    public Point getMiddle() {
        return middle;
    }

    public double getRadius() {
        return radius;
    }
    
    @Override
    public String toString() {
        return "Circle{" + "middle=" + middle + ", radius=" + radius + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.middle);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.radius) ^ (Double.doubleToLongBits(this.radius) >>> 32));
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
        final Circle other = (Circle) obj;
        if (Double.doubleToLongBits(this.radius) != Double.doubleToLongBits(other.radius))
            return false;
        return Objects.equals(this.middle, other.middle);
    }
    
    
}
