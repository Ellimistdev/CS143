// This program demonstrates the DrawingPanel's getPixels and
// setPixels methods for manipulating RGB integers of an image.

import java.awt.*;

public class Negative {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(300, 300);
        // panel.setBackground(Color.YELLOW);
        Image cat = panel.loadImage("grumpy-cat.png");
        Graphics g = panel.getGraphics();
        g.drawImage(cat, 10, 10, panel);
        negative(panel);
    }
    
    // Produces the negative of the given image by inverting all color
    // values in the panel.
    public static void negative(DrawingPanel panel) {
        Color[][] pixels = panel.getPixels();
        for (int row = 0; row < pixels.length; row++) {
        	for (int col = 0; col < pixels[0].length; col++) {
                // extract red/green/blue components from 0-255
                int r = 255 - pixels[row][col].getRed();
                int g = 255 - pixels[row][col].getGreen();
                int b = 255 - pixels[row][col].getBlue();
                
                // update the pixel array with the new color value
                pixels[row][col] = new Color(r, g, b);
            }
        }
        panel.setPixels(pixels);
    }
    
    // Makes the colors of every pixel of the given drawing panel
    // "brighter" by adding the given amount to them from 0-255.
    public static void brighten(DrawingPanel panel, int amount) {
        int[][] pixels = panel.getPixelsRGB();
        for (int x = 0; x < panel.getWidth(); x++) {
            for (int y = 0; y < panel.getHeight(); y++) {
                // extract red/green/blue components from 0-255
                int r = DrawingPanel.getRed(pixels[x][y]);
                int g = DrawingPanel.getGreen(pixels[x][y]);
                int b = DrawingPanel.getBlue(pixels[x][y]);
                
                // brighten each component by adding to it
                r = Math.min(255, r + amount);
                g = Math.min(255, g + amount);
                b = Math.min(255, b + amount);
                
                // update the pixel array with the new RGB value
                pixels[x][y] = DrawingPanel.toRgbInteger(r, g, b);
            }
        }
        panel.setPixelsRGB(pixels);
    }
}
