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

           for (int k = 0; k<numLight; k++) {
                light.add(lightArray[k].addedLight(intersect, ray.getOrigin(), this));
            }

            light.clamp();
            intersect.setColor(light);
        }

        return intersect;

    }

    public IntersectResult calcReflectionColor(Ray3 ray, int depth){
        IntersectResult finalResult = checkIntersect(ray);
        Ray3 reflectedRay = new Ray3();
        if(finalResult != null) {
            finalResult = calcFinalColor(finalResult, ray);
            reflectedRay.setOrigin(finalResult.getIntersect());
            reflectedRay.setDirection(ray.getDirection().scale(-1.0).reflect(finalResult.getNormal()));

            //scale reflectivity constant by direction of surface

            double reflectivityScale = 1.0 - finalResult.getNormal().dot(ray.getDirection().normalized());
            double minScaleFactor = 0.5;
            double maxScaleFactor = 3.0;
            reflectivityScale = (maxScaleFactor - minScaleFactor)*Math.pow(reflectivityScale,2.0) + minScaleFactor;



            Vector3 C = finalResult.getColor();
            double R = finalResult.getReflectivity()*reflectivityScale;
            if (R > 1){
                R = 1;
            }else if(R < 0){
                R = 0;
            }
            //define the variables I'm about to use: reflectivity constants, color pre-reflection

            if (depth > 0 && finalResult.getReflectivity() > 0.0) {
                //set color at point to include reflectivity
                Vector3 colorWithReflection = new Vector3();
                colorWithReflection = finalResult.getColor().scale(1 - R);
                colorWithReflection.add((calcReflectionColor(reflectedRay, depth - 1).getColor()).scale(R));
                finalResult.setColor(colorWithReflection);
                return finalResult;

            } else {
                return finalResult;
            }
        }else {
            finalResult = new IntersectResult(new Vector3(),new Vector3(),0, new Vector3(), 0);
            return finalResult;
        }
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
