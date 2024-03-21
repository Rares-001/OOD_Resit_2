package model;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * A slide item for representing bitmap images.
 */
public class BitmapItem extends SlideItem {
	private String imagePath;
	private BufferedImage image;

	/**
	 * Constructs a new BitmapItem with a specified level and image path
	 *
	 * @param level The level (indentation) of the item.
	 * @param imagePath The file path to the image.
	 */

	public BitmapItem(int level, String imagePath) {
		super(level);
		this.imagePath = imagePath;
		loadImage();
	}

	/**
	 * Loads the image from the specified imagePath.
	 */

	private void loadImage() {
		try {
			this.image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			System.err.println("Error loading image: " + imagePath);
			this.image = null;
		}
	}

	/**
	 * Draws the image onto the specified Graphics context.
	 *
	 * @param g The Graphics context to draw on.
	 * @param x The x coordinate of the upper-left corner where the image should be drawn.
	 * @param y The y coordinate of the upper-left corner where the image should be drawn.
	 * @param observer An object to be notified as more of the image is available.
	 */

	@Override
	public void draw(Graphics g, int x, int y, ImageObserver observer) {
		if (image != null) {
			g.drawImage(image, x, y, observer);
		} else {
			g.drawString("Image not found", x, y + 15); // show text if the image fails to load
		}
	}

	/**
	 * Calculates the height of the image.
	 *
	 * @param g The Graphics context used for drawing (not used in this method but required by the abstract class).
	 * @param observer An object to be notified as more of the image is available (not used in this method but required by the abstract method signature).
	 * @return The height of the image, or a default value if the image is not loaded.
	 */

	@Override
	public int getHeight(Graphics g, ImageObserver observer) {
		if (image != null) {
			return image.getHeight();
		}
		return 30; // Default height for the fallback text.
	}
}
