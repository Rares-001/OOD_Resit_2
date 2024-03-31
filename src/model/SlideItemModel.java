package model;

public abstract class SlideItemModel
{
    private final int level;
    private String content;

    public SlideItemModel(int level, String content)
    {
        this.level = level;
        this.content = content;
    }

    public int getLevel()
    {
        return level;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}

