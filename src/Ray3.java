/**
 * Created by kevin on 7/20/15.
 */
public class Ray3 {


    private Vector3 origin;
    private Vector3 direction;

    public Ray3(){
        origin = new Vector3();
        direction = new Vector3();
    }

    public Ray3(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray3(double a, double b, double c, double d, double e, double f){
        origin = new Vector3(a,b,c);
        direction = new Vector3(d,e,f);
    }

    public void translate(Vector3 otherVector){
        origin.add(otherVector);
    }

    public String toString(){
        return "The origin is " + origin + ". The direction is " + direction + ".";
    }

    public Vector3 getOrigin() {
        return origin;
    }

    public Vector3 getDirection(){ return direction; }

    public void setDirection(Vector3 direction){
        this.direction = direction;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }
}
