/**
 * Created by kevin on 8/6/15.
 */
public class Plane3 {
    private Vector3 origin;
    private Vector3 normal;
    private Vector3 color;

    public Plane3(){
        this(new Vector3(0,-100,0), new Vector3(1,1,0), new Vector3(0.5, 0.5, 0.5));

    }

    public Plane3(Vector3 point, Vector3 normal, Vector3 color){
        this.origin = point;
        this.normal = normal;
        this.color = color;
    }

    public Vector3 intersect(Ray3 ray){
        //intersection code

        double a = this.getNormal().dot(this.getOrigin());
        double b = this.getNormal().dot(ray.getOrigin());
        double c = this.getNormal().dot(ray.getDirection());
        double t;


        if (c != 0) {
            t = (a - b) / c;
        } else {
            return null;
        }

        if (t > 0){
            double x = ray.getDirection().getX()*t + ray.getOrigin().getX();
            double y = ray.getDirection().getY()*t + ray.getOrigin().getY();
            double z = ray.getDirection().getZ()*t + ray.getOrigin().getZ();

            Vector3 intersection = new Vector3(x, y, z);
            return intersection;
        } else{
            return null;
        }


    }


    public Vector3 getOrigin(){
        return origin;
    }

    public Vector3 getNormal(){
        return normal;
    }

    public Vector3 getColor() { return color;  }

}
