/**
 * Created by kevin on 8/7/15.
 */
public abstract class Geometry {
    private Vector3 color;
    private Vector3 origin;
    private double reflectivity;

    abstract public IntersectResult intersect(Ray3 ray);

    public Vector3 getColor(){ return color; }
    public Vector3 getOrigin(){ return origin; }
    public double getReflectivity(){ return reflectivity; }

    public void setColor(Vector3 color){
        this.color = color;
    }

    public void setOrigin(Vector3 origin){
        this.origin = origin;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }
}
