package model;

import java.awt.Color;
import java.awt.Font;

public class Style {
    private Font font;
    private Color color;
    private int leading;
    private int indent;

    public Style(Font font, Color color, int leading, int indent) {
        this.font = font;
        this.color = color;
        this.leading = leading;
        this.indent = indent;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLeading() {
        return leading;
    }

    public void setLeading(int leading) {
        this.leading = leading;
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }

    @Override
    public String toString() {
        return "Style{" +
                "font=" + font +
                ", color=" + color +
                ", leading=" + leading +
                ", indent=" + indent +
                '}';
    }
}
