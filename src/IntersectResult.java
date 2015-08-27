/**
 * Created by kevin on 8/7/15.
 */
public class IntersectResult {
    private Vector3 intersect;
    private Vector3 normal;
    private double distanceToRayOrigin;
    private Vector3 color;
    private double reflectivity;


    public IntersectResult(Vector3 intersect,Vector3 normal, double distanceToRayOrigin, Vector3 color, double reflectivity){
        this.intersect = intersect;
        this.normal = normal;
        this.distanceToRayOrigin = distanceToRayOrigin;
        this.color = color;
        this.reflectivity = reflectivity;
    }


    public Vector3 getColor() {
        return color;
    }

    public void setColor(Vector3 color){
        this.color = color;
    }

    public double getReflectivity(){
        return reflectivity;
    }

    public double getDistanceToCamera() {
        return distanceToRayOrigin;
    }

    public Vector3 getIntersect() {
        return intersect;
    }

    public Vector3 getNormal() {
        return normal;
    }

}
