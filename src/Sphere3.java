/**
 * Created by kevin on 7/20/15.
 */
public class Sphere3 {
    private Vector3 origin;
    private double radius;
    private Vector3 color;

    public Sphere3(){
        this(new Vector3(), 0);
    }

    public Sphere3(Vector3 origin, double radius){
        this(origin, radius, new Vector3 (0.5, 0.5, 0.5));
    }

    public Sphere3(Vector3 origin, double radius, Vector3 color){
        this.origin = origin;
        this.radius = radius;
        this.color = color;
    }

    public Sphere3(double x, double y, double z, double r){
        this(new Vector3 (x,y,z),r);
    }

    public void translate(Vector3 otherVector){
        origin.add(otherVector);
    }

    public void scale(double s){
        radius = s*radius;
    }

    public Vector3 intersect(Ray3 ray) {
        double a = ray.getOrigin().getX();
        double b = ray.getOrigin().getY();
        double c = ray.getOrigin().getZ();
        double d = ray.getDirection().getX();
        double e = ray.getDirection().getY();
        double f = ray.getDirection().getZ();
        double h = origin.getX();
        double i = origin.getY();
        double j = origin.getZ();
        double r = radius;

        double k = d * d + e * e + f * f;
        double l = 2 * (a - h) * d + 2 * (b - i) * e + 2 * (c - j) * f;
        double m = (a - h) * (a - h) + (b - i) * (b - i) + (c - j) * (c - j) - r * r;

        double discriminant = l * l - 4 * k * m;
        double t1;
        double t2;

        if (discriminant >= 0) {
            t1 = (-l + Math.sqrt(discriminant)) / (2 * k);
            t2 = (-l - Math.sqrt(discriminant)) / (2 * k);
        } else {
            return null;
        }

        if (t2 > t1) {
            Vector3 intersectPt = new Vector3(a + d * t1, b + e * t1, c + f * t1);
            return intersectPt;
        } else {
            Vector3 intersectPt = new Vector3(a + d * t2, b + e * t2, c + f * t2);
            return intersectPt;
        }
    }

    public Vector3 getOrigin(){
        return origin;
    }

    public double getRadius(){
        return radius;
    }

    public Vector3 getColor() {
        return color;
    }


//        if (discriminant < 0) {
//            return false;
//        } else {
//            return true;
//        }

}
