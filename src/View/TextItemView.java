package View;

import model.TextItemModel;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedString;

public class TextItemView extends SlideItemView {
    public TextItemView(TextItemModel model) {
        super(model);
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        TextItemModel textModel = (TextItemModel) getModel();
        AttributedString attrStr = new AttributedString(textModel.getText());
        attrStr.addAttribute(TextAttribute.FONT, textModel.getStyle().getFont(), 0, textModel.getText().length());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(textModel.getStyle().getColor());
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (SlideView.WIDTH - textModel.getStyle().getIndent()) * 1.0f; // Assume scale is 1 for simplicity

        while (measurer.getPosition() < textModel.getText().length()) {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            y += layout.getAscent();
            layout.draw(g2d, x, y);
            y += layout.getDescent() + layout.getLeading();
        }
    }


}
