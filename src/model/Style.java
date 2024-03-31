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
    private static final String labelFontName = "Dialog";
    private static final int labelFontStyle = Font.BOLD;
    private static final int labelFontSize = 10;
    private static final Color labelColor = Color.black;

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
            int index = level;
            if (index > 0 && index < styles.length)
            {
                return styles[index];
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

    public static Font getLabelFont()
    {
        return new Font(labelFontName, labelFontStyle, labelFontSize);
    }

    public static Color getLabelColor()
    {
        return labelColor;
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