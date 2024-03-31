package model;

public class TextItemModel extends SlideItemModel {
    private final Style style;

    public TextItemModel(int level, String text, Style style)
    {
        super(level, text);
        this.style = style;
    }

    public String getText()
    {
        return getContent();
    }

    public Style getStyle()
    {
        return style;
    }

}

