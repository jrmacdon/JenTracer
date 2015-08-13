/**
 * Created by kevin on 8/12/15.
 */
public abstract class Light {
    private Vector3 lightPosition;

    abstract public Vector3 addedLight(IntersectResult finalResult, Vector3 cameraPosition, Scene myScene);

    public Vector3 getLightPosition(){ return lightPosition; }

    public void setLightPosition(Vector3 lightPosition){ this.lightPosition = lightPosition; }
}
