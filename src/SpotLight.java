/**
 * Created by kevin on 8/19/15.
 */
public class SpotLight extends Light {
    private Vector3 spotLightDirection;
    private double angle;
    private double nearAngleAttentuation;
    private double farAngleAttenuation;

    public SpotLight(double x, double y, double z, double a, double b, double c, double nearAngle, double farAngle, double intensity, double near, double far){
        this.setLightPosition(new Vector3(x,y,z));
        spotLightDirection = new Vector3(a - x,b - y,c - z);
        this.angle = angle;
        nearAngleAttentuation = nearAngle;
        farAngleAttenuation = farAngle;
        this.setIntensity(intensity);
        this.setNearAttenuation(near);
        this.setFarAttenuation(far);
    }

    public SpotLight(Vector3 lightPosition, Vector3 lookAtPoint, double nearAngle, double farAngle, double intensity, double nearDistane, double farDistance){
        this.setLightPosition(lightPosition);
        spotLightDirection = lookAtPoint.minus(lightPosition);
        nearAngleAttentuation = nearAngle;
        farAngleAttenuation = farAngle;
        this.setIntensity(intensity);
        this.setNearAttenuation(nearDistane);
        this.setFarAttenuation(farDistance);
    }

    public Vector3 addedLight(IntersectResult finalResult, Vector3 cameraPosition, Scene myScene){
        //calculates specular and diffuse lighting for a particular light on a particular point.
        //May also calculate whether or not that point is in shadow and therefore the actual "added light" for that point.  If so, needs scene passed in, if not, scene can be removed.
        //returns a vector for the added light for a particular point/pixel

        //first calculate whether it is within the spotlight.  Then the addedLight can be calculated.

        Vector3 addedLight = new Vector3(0, 0, 0);
        Vector3 intersect = finalResult.getIntersect();
        Vector3 lightVector = getLightPosition().minus(intersect);
        lightVector.normalize();
        spotLightDirection.normalize();

        double angleBetween = Math.acos(spotLightDirection.dot(new Vector3().minus(lightVector)));
        angleBetween = angleBetween*180/(Math.PI);


        if (angleBetween < farAngleAttenuation) {
            Ray3 shadow = new Ray3(finalResult.getIntersect(), new Vector3(getLightPosition().minus(finalResult.getIntersect())));
            IntersectResult shadowResult = myScene.checkIntersect(shadow);
            if (shadowResult != null && shadowResult.getDistanceToCamera() > getLightPosition().minus(shadow.getOrigin()).getLength()) {
                shadowResult = null;
            }

            Vector3 normal = finalResult.getNormal();
            normal.normalize();
            Vector3 color = finalResult.getColor();


            Vector3 cameraVector = cameraPosition.minus(intersect);
            cameraVector.normalize();
            Vector3 camVecReflection = cameraVector.reflect(normal);
            camVecReflection.normalize();

            double diffuse_dp = normal.dot(lightVector);
            double s = Math.max(0, camVecReflection.dot(lightVector));
            s = Math.pow(s, 9) * 0.55 * diffuse_dp;


            Vector3 specular = new Vector3(s, s, s);

            Vector3 diffuse = new Vector3(0.7, 0.65, 0.5);
            diffuse.cwise(color);

            if (diffuse_dp >= 0 && shadowResult == null) {
                addedLight = diffuse.scale(diffuse_dp);//.add(specular);
            }

            //attenuation

            //double fade = Math.pow(0.998,getLightPosition().distanceTo(finalResult.getIntersect()));

            //addedLight = addedLight.scale(fade);

            double lightDistance = getLightPosition().distanceTo(finalResult.getIntersect());
            double distanceFade = 1;
            double angleFade = 1;


            addedLight.scale(getIntensity());

            if (nearAngleAttentuation < angleBetween && angleBetween < farAngleAttenuation){
                angleFade = (farAngleAttenuation - angleBetween) / (farAngleAttenuation - nearAngleAttentuation);
                addedLight.scale(angleFade);
            }

            if (lightDistance < getNearAttenuation()) {
                return addedLight;
            } else if (lightDistance < getFarAttenuation()) {
                distanceFade = (getFarAttenuation() - lightDistance) / (getFarAttenuation() - getNearAttenuation());
                return addedLight.scale(distanceFade);

            } else {
                return addedLight.scale(0);

            }
        }else{
            return addedLight;
        }
    }


}
