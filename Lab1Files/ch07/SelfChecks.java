// This program demonstrates the DrawingPanel's getPixels and
// setPixels methods for manipulating RGB integers of an image.

import java.awt.*;

public class SelfChecks {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(300, 300);
        // panel.setBackground(Color.YELLOW);
        Image cat = panel.loadImage("grumpy-cat.png");
        Graphics g = panel.getGraphics();
        g.drawImage(cat, 10, 10, panel);
        stretch(panel);
    }
    
    // show only red channel
    // loops that assume image is square in shape? forget [0] in length?
    
    public static void toRedChannel(DrawingPanel panel) {
        Color[][] pixels = panel.getPixels();
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[0].length; col++) {
                // your code goes here
            }
        }
        panel.setPixels(pixels);
    }
}
