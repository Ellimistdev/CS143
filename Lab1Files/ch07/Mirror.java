// This program contains a mirror method that flips the appearance
// of a DrawingPanel horizontally pixel-by-pixel.

import java.awt.*;

public class Mirror {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(300, 200);
        Graphics g = panel.getGraphics();
        g.drawString("Hello, world!", 20, 50);
        g.fillOval(10, 100, 20, 70);
        mirror(panel);
           
        Color c = new Color(100, 100, 100);
        System.out.println(c);
        c = c.brighter();
        System.out.println(c);
        c = c.brighter();
        System.out.println(c);
        c = c.brighter();
        System.out.println(c);
        c = c.darker();
        System.out.println(c);
        c = c.darker();
        System.out.println(c);
        c = c.darker();
        System.out.println(c);
        c = c.darker();
        System.out.println(c);
        c = c.darker();
        System.out.println(c);
        c = c.darker();
        System.out.println(c);
        c = c.darker();
        System.out.println(c);
    }
    
    // Flips the pixels of the given drawing panel horizontally.
    public static void mirror(DrawingPanel panel) {
        Color[][] pixels = panel.getPixels();
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length / 2; col++) {
                // swap with pixel at "mirrored" location
                int opposite = panel.getWidth() - 1 - col;
                Color px = pixels[row][col];
                pixels[row][col] = pixels[row][opposite];
                pixels[row][opposite] = px;
            }
        }
        panel.setPixels(pixels);
    }
}
