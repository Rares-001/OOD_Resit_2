package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitmapItemModel extends SlideItemModel
{
    private final String imageName;
    private transient BufferedImage image;
    public BitmapItemModel(int level, String imageName)
    {
        super(level, imageName);
        this.imageName = imageName;
        loadImage();
    }

    private void loadImage()
    {
        try {
            image = ImageIO.read(new File(getContent()));
        } catch (IOException e)
        {
            System.err.println("File " + getContent() + " not found");
            image = null;
        }
    }

    public void setContent(String imageName)
    {
        super.setContent(imageName);
        loadImage();
    }

    public String getImagePath()
    {
        return imageName;
    }
}


