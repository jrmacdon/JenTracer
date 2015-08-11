/**
 * Created by kevin on 8/7/15.
 */
public class IntersectResult {
    private Vector3 intersect;
    private Vector3 normal;
    private double distanceToCamera;
    private Vector3 color;


    public IntersectResult(Vector3 intersect,Vector3 normal, double distanceToCamera, Vector3 color){
        this.intersect = intersect;
        this.normal = normal;
        this.distanceToCamera = distanceToCamera;
        this.color = color;
    }


    public Vector3 getColor() {
        return color;
    }

    public double getDistanceToCamera() {
        return distanceToCamera;
    }

    public Vector3 getIntersect() {
        return intersect;
    }

    public Vector3 getNormal() {
        return normal;
    }

}
