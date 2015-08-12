/**
 * Created by kevin on 7/20/15.
 */
public class Sphere3 extends Geometry {
    private double radius;

    public Sphere3(){
        this(new Vector3(), 0);
    }

    public Sphere3(Vector3 origin, double radius){
        this(origin, radius, new Vector3 (0.5, 0.5, 0.5));
    }

    public Sphere3(Vector3 origin, double radius, Vector3 color){
        this.setOrigin(origin);
        this.radius = radius;
        this.setColor(color);
    }

    public Sphere3(double x, double y, double z, double r){
        this(new Vector3(x, y, z), r);
    }

    public void translate(Vector3 otherVector){
        getOrigin().add(otherVector);
    }

    public void scale(double s){
        radius = s*radius;
    }

    public IntersectResult intersect(Ray3 ray) {
        Vector3 intersect = null;
        double distanceToCamera;
        Vector3 normal;


        double a = ray.getOrigin().getX();
        double b = ray.getOrigin().getY();
        double c = ray.getOrigin().getZ();
        double d = ray.getDirection().getX();
        double e = ray.getDirection().getY();
        double f = ray.getDirection().getZ();
        double h = getOrigin().getX();
        double i = getOrigin().getY();
        double j = getOrigin().getZ();
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

        if (t1 > 0.00000000001 || t2 > 0.00000000001) {
            if (t2 > t1) {
                intersect = new Vector3(a + d * t1, b + e * t1, c + f * t1);
            } else {
                intersect = new Vector3(a + d * t2, b + e * t2, c + f * t2);
            }
        }
        //if (t1 < 0 || t2 <0) {
          //  System.out.println(t1 + ", " + t2);
        //}

        if (intersect != null) {
            normal = new Vector3(intersect.minus(getOrigin()));
            normal.normalize();
            distanceToCamera = intersect.distanceTo(ray.getOrigin());
            return new IntersectResult(intersect, normal, distanceToCamera, getColor());

        } else{
            return null;
        }

    }


    public double getRadius(){
        return radius;
    }



//        if (discriminant < 0) {
//            return false;
//        } else {
//            return true;
//        }

}
