package View;

import model.BitmapItemModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class BitmapItemView
{
    private final BitmapItemModel model;
    private BufferedImage image;

    public BitmapItemView(BitmapItemModel model)
    {
        this.model = model;
    }

    private void ensureImageLoaded()
    {
        if (image == null)
        {
            try {
                File imagePath = new File(model.getImagePath());
                image = ImageIO.read(imagePath);
            } catch (IOException e)
            {
                System.err.println("Error loading image: " + e.getMessage());
            }
        }
    }

    public int draw(Graphics g, int x, int y, float scale, ImageObserver observer)
    {
        ensureImageLoaded();
        if (image != null)
        {
            int width = (int) (image.getWidth(observer) * scale);
            int height = (int) (image.getHeight(observer) * scale);
            g.drawImage(image, x, y, width, height, observer);
            return height;
        } else {

            return 0;
        }
    }
}
