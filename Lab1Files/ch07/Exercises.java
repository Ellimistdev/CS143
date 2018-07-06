// This program demonstrates the DrawingPanel's getPixels and
// setPixels methods for manipulating RGB integers of an image.

import java.awt.*;

public class Exercises {
    public static void main(String[] args) {
        DrawingPanel panel = new DrawingPanel(110, 125);
        // panel.setBackground(Color.YELLOW);
        Image img = panel.loadImage("smiley-small.png");
        Graphics g = panel.getGraphics();
        g.drawImage(img, 5, 20, panel);
        g.drawString("Hello, world", 10, 15);
        blur(panel);
    }
    
    public static void blur(DrawingPanel panel) {
		int[][] pixels = panel.getPixelsRGB();
		int[][] newPixels = new int[pixels.length][pixels[0].length];
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				int rsum = 0;
				int gsum = 0;
				int bsum = 0;
				int count = 0;
				for (int r = row - 1; r <= row + 1; r++) {
					for (int c = col - 1; c <= col + 1; c++) {
						if (r >= 0 && r < pixels.length && c >= 0 && c < pixels[0].length) {
							count++;
							rsum += DrawingPanel.getRed(pixels[r][c]);
							gsum += DrawingPanel.getGreen(pixels[r][c]);
							bsum += DrawingPanel.getBlue(pixels[r][c]);
						}
					}
				}
				newPixels[row][col] = DrawingPanel.toRgbInteger(rsum / count, gsum / count, bsum
						/ count);
			}
		}
		panel.setPixelsRGB(newPixels);
    }
    
    public static void rotateLeft(DrawingPanel panel) {
		int[][] pixels = panel.getPixelsRGB();
		int[][] newPixels = new int[pixels[0].length][pixels.length];
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length; col++) {
				int newRow = col;
				int newCol = pixels.length - 1 - row;
				newPixels[newRow][newCol] = pixels[row][col];
			}
		}
		panel.setPixelsRGB(newPixels);
    }

    public static void rotateRight(DrawingPanel panel) {
    	for (int i = 0; i < 3; i++) {
    		rotateLeft(panel);
    	}
    }

    public static void transpose(DrawingPanel panel) {
        int[][] pixels = panel.getPixelsRGB();
        int[][] newPixels = new int[panel.getWidth()][panel.getHeight()];
        for (int x = 0; x < panel.getWidth(); x++) {
            for (int y = 0; y < panel.getHeight(); y++) {
                newPixels[x][y] = pixels[panel.getWidth() - 1 - x][panel.getHeight() - 1 - y];
            }
        }
        panel.setPixelsRGB(newPixels);
    }
    
    public static void grayscale(DrawingPanel panel) {
        int[][] pixels = panel.getPixelsRGB();
        for (int x = 0; x < panel.getWidth(); x++) {
            for (int y = 0; y < panel.getHeight(); y++) {
                int r = DrawingPanel.getRed(pixels[x][y]);
                int g = DrawingPanel.getGreen(pixels[x][y]);
                int b = DrawingPanel.getBlue(pixels[x][y]);
                int average = (r + g + b) / 3;
                pixels[x][y] = DrawingPanel.toRgbInteger(average, average, average);
            }
        }
        panel.setPixelsRGB(pixels);
    }
    
    public static void zoomIn(DrawingPanel panel) {
        int[][] pixels = panel.getPixelsRGB();
        int[][] newPixels = new int[2 * panel.getWidth()][2 * panel.getHeight()];
        for (int x = 0; x < 2 * panel.getWidth(); x++) {
            for (int y = 0; y < 2 * panel.getHeight(); y++) {
                newPixels[x][y] = pixels[x / 2][y / 2];
            }
        }
        panel.setPixelsRGB(newPixels);
    }
}
