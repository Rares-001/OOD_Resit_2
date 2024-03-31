package View;

import model.SlideItemModel;
import model.TextItemModel;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class SlideItemView {
    protected SlideItemModel model;

    public SlideItemView(SlideItemModel model) {
        this.model = model;
    }

    protected SlideItemModel getModel() {
        return this.model;
    }

    public abstract void draw(Graphics g, int x, int y); {
    }

}
