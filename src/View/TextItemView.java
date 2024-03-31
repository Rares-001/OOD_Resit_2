package View;

import model.Style;
import model.TextItemModel;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;

public class TextItemView
{
    private TextItemModel model;

    public TextItemView(TextItemModel model)
    {
        this.model = model;
    }

    public int draw(Graphics g, int x, int yInitial, float scale, int componentWidth)
    {
        Style style = model.getStyle();
        float wrappingWidth = componentWidth - (style.getIndent() * scale);
        System.out.println("Drawing TextItem: \"" + model.getText() + "\" with Style: Color=" + style.getColor() + ", FontSize=" + style.getFontSize());
        Font scaledFont = style.getFont(scale);
        g.setColor(style.getColor());
        g.setFont(scaledFont);
        AttributedString attrStr = new AttributedString(model.getText());
        attrStr.addAttribute(TextAttribute.FONT, scaledFont);
        attrStr.addAttribute(TextAttribute.FOREGROUND, style.getColor());
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        int totalHeight = 0;
        float y = yInitial;
        while (measurer.getPosition() < model.getText().length())
        {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            y += layout.getAscent();
            layout.draw(g2d, x + (style.getIndent() * scale), y);
            int lineHeight = (int)(layout.getAscent() + layout.getDescent() + layout.getLeading());
            y += layout.getDescent() + layout.getLeading();
            totalHeight += lineHeight;
        }

        return totalHeight;
    }
}
