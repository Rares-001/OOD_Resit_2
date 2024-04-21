package model;

import java.awt.Color;
import java.awt.Font;

public class Style
{
    private static Style[] styles;
    private static final String FONT_NAME = "Helvetica";
    private int indent;
    private Color color;
    private Font font;
    private int fontSize;
    private int leading;

    // New label-specific attributes
    private static final String LABEL_FONT_NAME = "Dialog";
    private static final int LABEL_FONT_STYLE = Font.BOLD;
    private static final int LABEL_FONT_SIZE = 10;

    public static void createStyles()
    {
        styles = new Style[]{
                new Style(0, Color.red, 48, 20),
                new Style(20, Color.blue, 40, 10),
                new Style(50, Color.black, 36, 10),
                new Style(70, Color.black, 30, 10),
                new Style(90, Color.black, 24, 10)
        };
    }

    private Style(int indent, Color color, int fontSize, int leading)
    {
        this.indent = indent;
        this.color = color;
        this.fontSize = fontSize;
        this.font = new Font(FONT_NAME, Font.BOLD, this.fontSize);
        this.leading = leading;
    }

    public static Style getStyle(int level)
    {
        if (level == 0)
        {
            return styles[0];
        } else
        {
            if (level > 0 && level < styles.length)
            {
                return styles[level];
            }

            return styles[styles.length - 1];
        }
    }

    public int getIndent()
    {
        return indent;
    }

    public Color getColor()
    {
        return color;
    }

    public Font getFont(float scale)
    {
        return font.deriveFont(fontSize * scale);
    }

    public static Font getLabelFont() {
        return new Font(LABEL_FONT_NAME, LABEL_FONT_STYLE, LABEL_FONT_SIZE);
    }

    public int getFontSize()
    {
        return fontSize;
    }

    public int getLeading()
    {
        return leading;
    }
}