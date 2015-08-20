/**
 * Created by kevin on 8/12/15.
 */
public abstract class Light {
    private Vector3 lightPosition;
    private double intensity;
    private double nearAttenuation;
    private double farAttenuation;

    abstract public Vector3 addedLight(IntersectResult finalResult, Vector3 cameraPosition, Scene myScene);

    public Vector3 getLightPosition(){
        return lightPosition;
    }

    public void setLightPosition(Vector3 lightPosition){
        this.lightPosition = lightPosition;
    }

    public double getNearAttenuation(){
        return nearAttenuation;
    }

    public void setNearAttenuation(double nearAttenuation){
        this.nearAttenuation = nearAttenuation;
    }

    public double getFarAttenuation(){
        return farAttenuation;
    }

    public void setFarAttenuation(double farAttenuation){
        this.farAttenuation = farAttenuation;
    }

    public double getIntensity(){
        return intensity;
    }

    public void setIntensity(double intensity){
        this.intensity = intensity;
    }
}
