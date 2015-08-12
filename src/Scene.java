/**
 * Created by kevin on 8/11/15.
 */
public class Scene {
    private Geometry[] geometryArray;
    private int numGeometry;

    public Scene(int i){
        geometryArray = new Geometry[i];
        numGeometry = 0;
    }

    public IntersectResult castRay(Ray3 ray){
        //intersection code to be placed here
        Ray3 test =ray;
        IntersectResult finalResult = null;

        for (int k = 0; k<numGeometry; k++) {
            //Calculate the intersection point for each object here, then choose which one to keep
            IntersectResult currentResult = geometryArray[k].intersect(test);

            if (currentResult != null) {

                if (finalResult == null) {
                    finalResult = currentResult;

                } else if (currentResult.getDistanceToCamera() < finalResult.getDistanceToCamera()) {
                    finalResult = currentResult;

                }
            }
        }
        return finalResult;

    }

    public void put(Geometry object){
       // if (numGeometry == geometryArray.length) {
         //   geometryArray.expandStorage();

       // }
        if (numGeometry < geometryArray.length){
            geometryArray[numGeometry] = object;
            numGeometry++;
        }
    }

    public int getNumGeometry() {
        return numGeometry;
    }
/*
    private void expandStorage(){
       /* Geometry[] newArray = new Geometry[geometryArray.length + 1];
        return newArray;
        OR
        geometryArray = Geometry[geometryArray.length + 1];
        return this;

        //expands the size of geometryArray by 1 index.  Don't know how to do this. Don't want to create a new array, just want the current array to be one index longer.
        return geometryArray;
    } */
}
