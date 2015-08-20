/**
 * Created by kevin on 8/12/15.
 */
public class PointLight extends Light {

    public PointLight(double x, double y, double z){
        this.setLightPosition(new Vector3(x,y,z));
    }

    public Vector3 addedLight(IntersectResult finalResult, Vector3 cameraPosition, Scene myScene){
        //calculates specular and diffuse lighting for a particular light on a particular point.
        //May also calculate whether or not that point is in shadow and therefore the actual "added light" for that point.  If so, needs scene passed in, if not, scene can be removed.
        //returns a vector for the added light for a particular point/pixel

        Ray3 shadow = new Ray3(finalResult.getIntersect(), new Vector3(getLightPosition().minus(finalResult.getIntersect())));
        IntersectResult shadowResult = myScene.castRay(shadow);
        if (shadowResult != null && shadowResult.getDistanceToCamera() > getLightPosition().minus(shadow.getOrigin()).getLength()){
            shadowResult = null;
        }

        Vector3 normal = finalResult.getNormal();
        normal.normalize();
        Vector3 color = finalResult.getColor();
        Vector3 intersect = finalResult.getIntersect();


        Vector3 lightVector = getLightPosition().minus(intersect);
        lightVector.normalize();

        Vector3 cameraVector = cameraPosition.minus(intersect);
        cameraVector.normalize();
        Vector3 camVecReflection = cameraVector.reflect(normal);
        camVecReflection.normalize();

        double diffuse_dp = normal.dot(lightVector);
        double s = Math.max(0, camVecReflection.dot(lightVector));
        s = Math.pow(s, 9) * 0.55 * diffuse_dp;


        Vector3 specular = new Vector3(s, s, s);
        Vector3 addedLight = new Vector3(0,0,0);

        Vector3 diffuse = new Vector3(0.7, 0.65, 0.5);
        diffuse.cwise(color);

        if (diffuse_dp >= 0 && shadowResult == null) {
            addedLight = diffuse.scale(diffuse_dp).add(specular);
        }

        //attenuation

        //double fade = Math.pow(0.998,getLightPosition().distanceTo(finalResult.getIntersect()));

        //addedLight = addedLight.scale(fade);

        double lightDistance = getLightPosition().distanceTo(finalResult.getIntersect());
        double fade = 1;
        double nearAttenuation = 5;
        double farAttenuation = 28;

        addedLight.scale(3);

        if(lightDistance < nearAttenuation) {
            return addedLight;
        }else if(lightDistance < farAttenuation){
            fade = (farAttenuation - lightDistance)/(farAttenuation-nearAttenuation);
            return addedLight.scale(fade);

        }else{
            return addedLight.scale(0);

        }
    }
}
