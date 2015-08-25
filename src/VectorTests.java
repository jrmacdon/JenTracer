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

        Scene myScene = new Scene(6,3);
        myScene.put(new Sphere3(new Vector3 (0,1,0),2, new Vector3 (0.2, 0.5, 0.1)));
        myScene.put(new Sphere3(new Vector3 (-3,3.5,0.7),0.1, new Vector3 (0.1, 0.2, 0.4)));
        myScene.put(new Sphere3(new Vector3 (3,0,0),2, new Vector3 (0.8, 0.2, 0.2)));
        myScene.put(new Sphere3(new Vector3 (0,4,-5),1, new Vector3 (0.5, 0.2, 0.2)));
        myScene.put(new Sphere3(new Vector3 (-6,3,-2),0.5, new Vector3 (0.1, 0.3, 0.2)));
        myScene.put(new Sphere3(new Vector3 (8,1,-20),8, new Vector3 (0.3, 0.2, 0.7)));
        myScene.put(new Sphere3(new Vector3 (-2,1.5,8),0.3));
        myScene.put(new Plane3(new Vector3(0,0,0), new Vector3(0,1,0), new Vector3(0.5, 0.2, 0.5)));

        myScene.put(new PointLight(-10, 10, 15, .75, 5, 28));
        myScene.put(new PointLight(-10,5,-100, 5 , 5, 35));
        myScene.put(new SpotLight(new Vector3(-20, 10, 0), new Vector3(0, 0, 0), 7, 40, 5, 60, 200));
        myScene.put(new Plane3(new Vector3(12,0,0), new Vector3(-3,-1,0), new Vector3(0.8, 0.5, 0.8)));
        //myScene.put(new Plane3(new Vector3(0,0,-150), new Vector3(0,0,-1), new Vector3(0.5, 0.5, 0.5)));

        Vector3 cameraPosition = new Vector3(0,1,20);

        double pixelWidth = worldWidth/screenWidth;

        double cameraDistance = 7;

        for (int i = 0; i < screenHeight; i++) {
            for (int j = 0; j < screenWidth; j++) {
                Vector3 worldPixel = new Vector3(-0.5*worldWidth + cameraPosition.getX() + 0.5*pixelWidth + j*pixelWidth, 0.5*worldHeight + cameraPosition.getY() - 0.5*pixelWidth - i*pixelWidth,cameraPosition.getZ() - cameraDistance);

                Vector3 cameraDirection = new Vector3(worldPixel.minus(cameraPosition));

                Ray3 test = new Ray3(cameraPosition, cameraDirection);
                IntersectResult finalResult = myScene.castRay(test);

                if (finalResult != null) {

                    surface.renderPixel(j, i, (float) finalResult.getColor().getX(), (float) finalResult.getColor().getY(), (float) finalResult.getColor().getZ());

                }
            }
        }

    }

}
