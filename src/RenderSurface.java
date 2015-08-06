import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by kevin on 7/13/15.
 */
public class RenderSurface extends  JPanel {

    private BufferedImage canvas;
    private int sleep;

    public RenderSurface(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(Color.BLACK);


    }

    public void openSurfaceInWindow() {
        JFrame frame = new JFrame("Direct draw demo");

        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void renderPixel(int x, int y, float r, float g, float b) {
        try {
            canvas.setRGB(x, y, new Color(r, g, b, 1).getRGB());
        } catch (ArrayIndexOutOfBoundsException e) {
          //  System.out.println("Coords out of bounds: " + x + ", " + y);
        }
        repaint();
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return canvas.getWidth();
    }

    public int getHeight() {
        return canvas.getHeight();
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }


    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
}
