/**
 * Created by kevin on 7/13/15.
 */
public class RayTracer {

    public static void drawRectangle(int width, int height, int x, int y, RenderSurface s) {
        if (( x >= s.getWidth())
                || (x + width < 0 )
                || (y >= s.getHeight())
                || (y + height < 0)
                || (x < 0 && y < 0 && x + width >= s.getWidth() && y + height >= s.getHeight())) {
            System.out.println("The rectangle is not on the screen.");
        } else {
            for (int i = x; i < x + width; i++) {
                s.renderPixel(i, y, 1, 1, 1);
                s.renderPixel(i, y + height, 1, 1, 1);
            }
            for (int j = y; j < y + height; j++) {
                s.renderPixel(x, j, 1, 1, 1);
                s.renderPixel(x + width, j, 1, 1, 1);
            }
        }

    }



    public static void main(String[] args) {

        RenderSurface surface = new RenderSurface(640, 480);
        surface.openSurfaceInWindow();
        surface.setSleep(0);


        int rect_one_width = 200;


        drawRectangle(rect_one_width, 300, 100, 150, surface);
        drawRectangle(300,50,800,120,surface);
        drawRectangle(800,800,-100,-50,surface);
        drawRectangle(300,200,-100,-50,surface);
//
//        for (int i = 0; i < 640; i++) {
//            for (int j = 0; j < 480; j++) {
//                surface.renderPixel(i, j, i/640.0f, (float) (Math.cos(j/480.0f*6.0f*Math.PI) + 1.0f)/2.0f, 1.0f);
//            }
//        }
//
//        for (int i = 0; i < 3000; i++) {
//            surface.renderPixel((int)(i/10.0f*Math.cos(i*2*Math.PI/300.0)+200),(int)(i/10.0f*Math.sin(i*2*Math.PI/300.0)+200),0,0,0);
//        }

//       for (int i = 100; i < 301; i++) {
//           surface.renderPixel(i,
//                   (int) (200 + Math.sqrt(100 * 100 - Math.pow(i - 200, 2))),
//                   0.5f, 0.5f, 0.5f);
//           surface.renderPixel(i,
//                   (int) (200 - Math.sqrt(100 * 100 - Math.pow(i - 200, 2))),
//                   0.5f, 0.5f, 0.5f);
//       }


//        for (int i = 0; i < 10000; i++) {
//            surface.renderPixel((int) (Math.random() * 640), (int) (Math.random() * 480), 1,1,1);
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }
}
