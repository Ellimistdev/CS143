import java.awt.*;
// import java.awt.event.*;

public class TestPixels {
	public static void main(String[] args) {
		final DrawingPanel panel = new DrawingPanel(600, 400);
		Graphics g = panel.getGraphics();
		g.setColor(Color.RED);
		g.fillRect(10, 20, 60, 120);
		
//		for (int x = 5; x <= 30; x++) {
//			for (int y = 80; y <= 100; y++) {
//				int rgb = panel.getPixel(x, y);
//				int r = DrawingPanel.getRed(rgb);
//				int gr = DrawingPanel.getGreen(rgb);
//				int b = DrawingPanel.getBlue(rgb);
//				System.out.printf("pixel (%3d, %3d) = %d (r=%3d, g=%3d, b=%3d)\n", x, y, rgb, r, gr, b);
//			}
//		}
		
		for (int x = 5; x <= 30; x++) {
			for (int y = 80; y <= 100; y++) {
				panel.setPixel(x, y, Color.GREEN);
			}
		}
		
		// swap r/g
//		Color[][] pixels = panel.getPixelColors();
//		for (int x = 0; x < pixels.length; x++) {
//			for (int y = 0; y < pixels[0].length; y++) {
//				int green = pixels[x][y].getGreen();
//				int red = pixels[x][y].getRed();
//				int blue = pixels[x][y].getBlue();
//				pixels[x][y] = new Color(green, red, blue).darker().darker();
//			}
//		}
//		panel.setPixelColors(pixels);
		
//		panel.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				int x = e.getX();
//				int y = e.getY();
//				g.fillOval(x, y, 10, 10);
//			}
//		});
		
		g.setColor(Color.RED);
		panel.onClick( (x, y) -> g.fillOval(x-5, y-5, 10, 10) );
	}
}
