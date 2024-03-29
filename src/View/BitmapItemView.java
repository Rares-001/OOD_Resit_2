package View;

import model.BitmapItemModel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BitmapItemView extends SlideItemView {

    public BitmapItemView(BitmapItemModel model) {
        super(model);
        this.bitmapItemModel = model;
        loadImage();
    }

    private final BitmapItemModel bitmapItemModel;
    private Image image;

    // Load the image from the path provided by the BitmapItemModel
    private void loadImage() {
        try {
            File imagePath = new File(bitmapItemModel.getImagePath());
            this.image = ImageIO.read(imagePath);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            this.image = null;
        }
    }

    public void draw(Graphics g, int x, int y) {
        if (image != null) {
            // Draw the image at the specified location x, y
            g.drawImage(image, x, y, null);
        } else {
            // Optionally handle missing image still to implement laterly
            g.drawString("Image not found", x, y);
        }
    }

}
