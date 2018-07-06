// This program demonstrates the DrawingPanel's getPixels and
// setPixels methods for manipulating RGB integers of an image.

import java.awt.*;

public class Stretch {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(300, 300);
        // panel.setBackground(Color.YELLOW);
        Image cat = panel.loadImage("grumpy-cat.png");
        Graphics g = panel.getGraphics();
        g.drawImage(cat, 10, 10, panel);
        stretch(panel);
    }
    
    // Stretches the given panel to be twice as wide.
    // Any shapes and colors drawn on the panel are stretched to fit.
    public static void stretch(DrawingPanel panel) {
        Color[][] pixels = panel.getPixels();
        Color[][] newPixels = new Color[pixels.length][2 * pixels[0].length];
        for (int row = 0; row < pixels.length; row++) {
        	for (int col = 0; col < 2 * pixels[0].length; col++) {
                newPixels[row][col] = pixels[row][col / 2];
            }
        }
        panel.setPixels(newPixels);
    }
}
