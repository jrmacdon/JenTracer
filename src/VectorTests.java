/**
 * Created by kevin on 7/16/15.
 */
public class VectorTests {

    public static void main(String[] args) {

        int screenWidth = 1000;
        int screenHeight = 800;
        RenderSurface surface = new RenderSurface(screenWidth,screenHeight);
        surface.openSurfaceInWindow();
        surface.setSleep(0);

        double worldWidth = 7;
        double aspectRatio = (double) screenWidth/screenHeight;
        double worldHeight = worldWidth/aspectRatio;
        Sphere3[] sphereArray = new Sphere3[4];
        sphereArray[0] = new Sphere3(new Vector3 (0,1,0),2, new Vector3 (0.2, 0.5, 0.1));
        sphereArray[1] = new Sphere3(new Vector3 (-3,0,0),3, new Vector3 (0.1, 0.2, 0.4));
        sphereArray[2] = new Sphere3(new Vector3 (3,0,0),2, new Vector3 (0.8, 0.2, 0.2));
        sphereArray[3] = new Sphere3(new Vector3 (0,3.5,0),1);

        Plane3 firstPlane = new Plane3(new Vector3(0,0,0), new Vector3(0,1,0), new Vector3(0.5, 0.2, 0.5));

        Vector3 cameraPosition = new Vector3(0,1,20);
        Vector3 lightPosition = new Vector3(-30, 40, 5);
        double pixelWidth = worldWidth/screenWidth;

        double cameraDistance = 10;





        for (int i = 0; i < screenHeight; i++) {
            for (int j = 0; j < screenWidth; j++) {
                Vector3 worldPixel = new Vector3(-0.5*worldWidth + cameraPosition.getX() + 0.5*pixelWidth + j*pixelWidth, 0.5*worldHeight + cameraPosition.getY() - 0.5*pixelWidth - i*pixelWidth,cameraPosition.getZ() - cameraDistance);

                Vector3 cameraDirection = new Vector3(worldPixel.minus(cameraPosition));
                Ray3 test = new Ray3(cameraPosition, cameraDirection);
                Vector3 intersect = null;

                double finalDistance = 999999999999.9;
                int sphereIdentity = 0;

                Vector3 normal = null;
                Vector3 color = null;




                for (int k = 0; k<sphereArray.length; k++) {
                    //Calculate the intersection point for each sphere here
                    Vector3 intersectPoint = sphereArray[k].intersect(test);
                    if (intersectPoint != null) {
                        double distance1 = intersectPoint.distanceTo(cameraPosition);

                        if (intersect == null) {
                            intersect = intersectPoint;
                            finalDistance = distance1;
                            sphereIdentity = k;
                        } else if (distance1 < finalDistance) {
                            intersect = intersectPoint;
                            finalDistance = distance1;
                            sphereIdentity = k;

                        }
                    }
                }

                if (intersect != null) {
                    normal = new Vector3(intersect.minus(sphereArray[sphereIdentity].getOrigin()));
                    normal.normalize();
                    color = new Vector3 (sphereArray[sphereIdentity].getColor());
                }

                    //calculates the intersection point of the plane and names the normal and color if the plane is in front of a sphere.
                Vector3 intersectPoint = firstPlane.intersect(test);
                if (intersectPoint != null) {
                    double distance1 = intersectPoint.distanceTo(cameraPosition);

                    if (distance1 < finalDistance) {
                        intersect = intersectPoint;
                        finalDistance = distance1;
                        normal = new Vector3(firstPlane.getNormal());
                        normal.normalize();
                        color = new Vector3 (firstPlane.getColor());

                    }
                }


                if (intersect != null) {


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
                    Vector3 light = new Vector3();

                    Vector3 ambient = new Vector3(0.1, 0.1, 0.24);
                    ambient.cwise(color);
                    Vector3 diffuse = new Vector3(0.7, 0.65, 0.5);
                    diffuse.cwise(color);

                    if (normal.dot(lightVector) >= 0) {
                        light = diffuse.scale(normal.dot(lightVector)).add(ambient).add(specular);
                        light.clamp();
                    } else {
                        light = ambient;
                    }

                    surface.renderPixel(j, i, (float) light.getX(), (float) light.getY(), (float) light.getZ());


                }
            }
        }







    }
}
