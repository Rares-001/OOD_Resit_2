package model;

public class TextItemModel extends SlideItemModel {
    private String text;
    private Style style;

    public TextItemModel(int level, String text, Style style) {
        super(level, text);
        this.text = text;
        this.style = style;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        setContent(text);
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}

