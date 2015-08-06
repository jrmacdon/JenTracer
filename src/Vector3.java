/**
 * Created by kevin on 7/16/15.
 */
public class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        
    }

    public Vector3(Vector3 vector) {
        x = vector.getX();
        y = vector.getY();
        z = vector.getZ();
    }

    public Vector3 scale(double s){
        x = s*x;
        y = s*y;
        z = s*z;
        return this;
    }

    public String toString(){
        return "(" + x + ", " + y + ", " + z + ")";
    }

    public double getLength(){
        return Math.sqrt(x*x+y*y+z*z);

    }

    public Vector3 normalize(){
        double magnitude = getLength();
        x = x / magnitude;
        y = y / magnitude;
        z = z / magnitude;
        return this;
    }

    public Vector3 add(Vector3 otherVector){
        x = x + otherVector.x;
        y = y + otherVector.y;
        z = z + otherVector.z;
        return this;
    }

    public double getX(){
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double dot(Vector3 o){
        return x*o.x + y*o.y + z*o.z;
    }

    public Vector3 normalized(){
        Vector3 normed = new Vector3(x,y,z);
        return normed.normalize();

    }

    public Vector3 cross(Vector3 other){
        Vector3 crossed = new Vector3();
        crossed.x = y*other.z - z*other.y;
        crossed.y = -(x*other.z - z*other.x);
        crossed.z = x*other.y - y*other.x;
        return crossed;
    }

    public Vector3 proj(Vector3 onto){
        Vector3 projection = onto.normalized();
        double scale = this.dot(projection);
        projection.scale(scale);
        return projection;

    }

    public Vector3 minus(Vector3 otherVector){
        Vector3 difference = new Vector3();
        difference.x = x - otherVector.x;
        difference.y = y - otherVector.y;
        difference.z = z - otherVector.z;
        return difference;
    }

    public Vector3 reflect(Vector3 across){
        Vector3 a = new Vector3(this.proj(across));
        Vector3 reflection = a.scale(2).minus(this);
        return reflection;
    }

    public Vector3 clamp(){
        if (x > 1){
            x = 1;
        }
        if (y > 1){
            y = 1;
        }
        if (z > 1){
            z = 1;
        }
        return this;
    }

    public double distanceTo(Vector3 otherVector){
        return this.minus(otherVector).getLength();
    }

    public void cwise(Vector3 color) {
        x = x * color.x;
        y = y * color.y;
        z = z * color.z;
    }
}
