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
/*
    public IntersectResult castRay(){
        //intersection code to be placed here

    }
*/
    public void put(Geometry object){
       // if (numGeometry == geometryArray.length) {
         //   geometryArray.expandStorage();

       // }
        if (numGeometry < geometryArray.length){
            geometryArray[numGeometry] = object;
            numGeometry++;
        }
    }
/*
    private Geometry[] expandStorage(){
       /* Geometry[] newArray = new Geometry[geometryArray.length + 1];
        return newArray;
        OR
        geometryArray = Geometry[geometryArray.length + 1];
        return this;

        //expands the size of geometryArray by 1 index.  Don't know how to do this. Don't want to create a new array, just want the current array to be one index longer.
        return geometryArray;
    } */
}
