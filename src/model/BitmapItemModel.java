package model;

public class BitmapItemModel extends SlideItemModel {
    private String imageName;

    public BitmapItemModel(int level, String imageName) {
        super(level, imageName);
        this.imageName = imageName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * Retrieves the image path.
     * In the provided old code, specifics of this implementation were not detailed.
     * Here it returns the imageName, assuming it could be used directly or modified
     * to construct a path.
     *
     * @return The image name or path.
     */
    public String getImagePath() {
        // Return imageName or modify to construct a path
        return imageName;
    }
}


