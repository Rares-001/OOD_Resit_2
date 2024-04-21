package model;

public class TextItemModel extends SlideItemModel {
    private final Style style; // immutability of this attribute.

    /**
     * Constructs a new TextItemModel with specified level, text, and style.
     * @param level the hierarchical level of the text in the presentation
     * @param text the text content of the item
     * @param style the style to apply to the text item, cannot be null
     * @throws IllegalArgumentException if style is null
     */
    public TextItemModel(int level, String text, Style style) {
        super(level, text);
        if (style == null) {
            throw new IllegalArgumentException("Style cannot be null");
        }
        this.style = style;
    }

    /**
     * Returns the text content of this item.
     * @return the text content
     */
    public String getText()
    {
        return getContent();
    }

    /**
     * Returns the style associated with this text item.
     * @return the style
     */
    public Style getStyle()
    {
        return style;
    }

}

