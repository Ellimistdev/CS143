// This program demonstrates the DrawingPanel's getPixelColors and
// setPixelColors methods for manipulating pixels of an image.

import java.awt.*;

public class DrawPurpleTriangle {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(300, 200);
        Color[][] pixelColors = panel.getPixels();
        for (int row = 50; row <= 150; row++) {
            for (int col = 50; col <= row; col++) {
                pixelColors[row][col] = Color.MAGENTA;
            }
        }
        panel.setPixels(pixelColors);
    }
}
