package view;

import model.SlideItemModel;

import java.awt.Graphics;

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
