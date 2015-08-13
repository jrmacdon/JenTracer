/**
 * Created by kevin on 8/12/15.
 */
public class PointLight {
    private Vector3 lightPosition;

    public PointLight(double x, double y, double z){
        lightPosition = new Vector3(x,y,z);
    }

    public Vector3 addedLight(IntersectResult finalResult, Vector3 cameraPosition, Scene myScene){
        //calculates specular and diffuse lighting for a particular light on a particular point.
        //May also calculate whether or not that point is in shadow and therefore the actual "added light" for that point.  If so, needs scene passed in, if not, scene can be removed.
        //returns a vector for the added light for a particular point/pixel

        Ray3 shadow = new Ray3(finalResult.getIntersect(), new Vector3(lightPosition.minus(finalResult.getIntersect())));
        IntersectResult shadowResult = myScene.castRay(shadow);
        if (shadowResult != null && shadowResult.getDistanceToCamera() > lightPosition.minus(shadow.getOrigin()).getLength()){
            shadowResult = null;
        }

        Vector3 normal = finalResult.getNormal();
        normal.normalize();
        Vector3 color = finalResult.getColor();
        Vector3 intersect = finalResult.getIntersect();


        Vector3 lightVector = lightPosition.minus(intersect);
        lightVector.normalize();

        Vector3 cameraVector = cameraPosition.minus(intersect);
        cameraVector.normalize();
        Vector3 camVecReflection = cameraVector.reflect(normal);
        camVecReflection.normalize();

        double diffuse_dp = normal.dot(lightVector);
        double s = Math.max(0, camVecReflection.dot(lightVector));
        s = Math.pow(s, 5) * 0.85 * diffuse_dp;


        Vector3 specular = new Vector3(s, s, s);
        Vector3 addedLight = new Vector3(0,0,0);

        Vector3 diffuse = new Vector3(0.7, 0.65, 0.5);
        diffuse.cwise(color);

        if (normal.dot(lightVector) >= 0 && shadowResult == null) {
            addedLight = diffuse.scale(normal.dot(lightVector)).add(specular);
        }

        return addedLight;
    }
}
