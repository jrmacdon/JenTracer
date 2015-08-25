/**
 * Created by kevin on 8/11/15.
 */
public class Scene {
    private Geometry[] geometryArray;
    private int numGeometry;
    private Light[] lightArray;
    private int numLight;

    public Scene(int i, int j){
        geometryArray = new Geometry[i];
        numGeometry = 0;
        lightArray = new Light[j];
        numLight = 0;
    }

    public IntersectResult castRay(Ray3 ray){
        IntersectResult finalResult = checkIntersect(ray);
        if(finalResult != null){
            finalResult = calcFinalColor(finalResult, ray);
        }
        return finalResult;
    }

    public IntersectResult checkIntersect(Ray3 ray){
        //intersection code to be placed here
        Ray3 test =ray;
        IntersectResult finalIntersect = null;

        for (int k = 0; k<numGeometry; k++) {
            //Calculate the intersection point for each object here, then choose which one to keep
            IntersectResult currentIntersect = geometryArray[k].intersect(test);

            if (currentIntersect != null) {

                if (finalIntersect == null) {
                    finalIntersect = currentIntersect;

                } else if (currentIntersect.getDistanceToCamera() < finalIntersect.getDistanceToCamera()) {
                    finalIntersect = currentIntersect;
                }
            }
        }
        return finalIntersect;
    }

    public IntersectResult calcFinalColor(IntersectResult intersect, Ray3 ray){

       if (intersect != null) {

            Vector3 ambient = new Vector3(0.1, 0.1, 0.3);
            ambient.cwise(intersect.getColor());
            Vector3 light = new Vector3(ambient);

            for (Light eachLight : lightArray) {
                light.add(eachLight.addedLight(intersect, ray.getOrigin(), this));
            }

            light.clamp();
            intersect.setColor(light);
        }

        return intersect;

    }

    public void put(Geometry object){
       if (numGeometry == geometryArray.length) {
          this.expandStorage();
       }
        if (numGeometry < geometryArray.length){
            geometryArray[numGeometry] = object;
            numGeometry++;
        }

    }

    public void put(Light object){
        if (numLight == lightArray.length) {
            this.expandStorage(); //need expand storage to work with light arrays too
        }
        if (numLight < lightArray.length){
            lightArray[numLight] = object;
            numLight++;
        }

    }

    public int getNumGeometry() {
        return numGeometry;
    }

    public void expandStorage(){
        Geometry[] newArray = new Geometry[2*geometryArray.length];

        for (int n = 0; n < geometryArray.length; n++){
            newArray[n] = geometryArray[n];
        }

        geometryArray = newArray;
    }
}
