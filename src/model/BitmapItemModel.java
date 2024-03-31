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

    public BufferedImage getImage()
    {
        if (image == null)
        {
            loadImage();
        }
        return image;
    }

    public String getImageName()
    {
        return imageName;
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


