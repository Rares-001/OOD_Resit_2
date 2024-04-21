package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitmapItemModel extends SlideItemModel
{
    private String imageName;
    private transient BufferedImage image;
    public BitmapItemModel(int level, String imageName)
    {
        super(level, imageName);
        this.imageName = imageName;
        loadImage();
    }

    private void loadImage() {
        File imageFile = new File(imageName);
        if (imageFile.exists()) {
            try {
                image = ImageIO.read(imageFile);
            } catch (IOException e) {
                System.err.println("Error loading image from " + imageName + ": " + e.getMessage());
                image = null;
            }
        } else {
            System.err.println("Image file not found: " + imageName);
            image = null;
        }
    }

    public void setContent(String imageName) {
        super.setContent(imageName);
        this.imageName = imageName;
        loadImage();
    }

    public String getImagePath()
    {
        return imageName;
    }
}


