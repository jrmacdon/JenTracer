/**
 * Created by kevin on 8/6/15.
 */
public class Plane3 extends Geometry {
    private Vector3 normal;

    public Plane3(){
        this(new Vector3(0,0,0), new Vector3(0,1,0), new Vector3(0.5, 0.5, 0.5));

    }

    public Plane3(Vector3 point, Vector3 normal, Vector3 color){
        this.setOrigin(point);
        this.normal = normal;
        this.setColor(color);
    }

    public IntersectResult intersect(Ray3 ray){
        //intersection code

        Vector3 intersection = null;
        double distanceToCamera;

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

            intersection = new Vector3(x, y, z);
        }

        if (intersection != null){
            distanceToCamera = intersection.distanceTo(ray.getOrigin());
            IntersectResult result = new IntersectResult(intersection, normal, distanceToCamera, getColor());
            return result;
        } else{
            return null;
        }

    }


    public Vector3 getNormal(){
        return normal;
    }


}
