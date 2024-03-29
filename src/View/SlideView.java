package View;

import model.SlideModel;
import model.SlideItemModel;
import model.TextItemModel;
import model.BitmapItemModel;
import observer.Observable;
import observer.Observer;

import java.awt.*;
import javax.swing.JComponent;

public class SlideView extends JComponent implements Observer {
    private SlideModel slideModel;
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color TEXT_COLOR = Color.black;
    private static final String FONT_NAME = "Dialog";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 10;
    public static final int WIDTH = 1200; // Making public for access from MainView
    public static final int HEIGHT = 800; // Making public for access from MainView

    public SlideView(SlideModel slideModel) {
        super();
        this.setBackground(BACKGROUND_COLOR);
    }

    public void setSlideModel(SlideModel slideModel) {
        if (this.slideModel != null) {
            this.slideModel.removeObserver(this);
        }

        this.slideModel = slideModel;
        slideModel.addObserver(this);
        repaint(); // Repaint the view with the new model's data
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (slideModel != null) {
            drawSlide(g);
        }
    }

    private void drawSlide(Graphics g) {
        g.setColor(TEXT_COLOR);
        g.setFont(new Font(FONT_NAME, FONT_STYLE, FONT_SIZE));
        // Start drawing from a base position, adjusted for content
        int yPosition = 50;

        // Draw the slide title
        g.drawString(slideModel.getTitle(), 50, yPosition);
        yPosition += 60; // Space before drawing items

        // Draw each slide item
        for (SlideItemModel item : slideModel.getItems()) {
            if (item instanceof TextItemModel) {
                drawTextItem(g, (TextItemModel)item, 50, yPosition);
                yPosition += 50; // Increment for the next item
            } else if (item instanceof BitmapItemModel) {
                drawBitmapItem(g, (BitmapItemModel)item, 50, yPosition);
                yPosition += 100;
            }
        }
    }

    private void drawTextItem(Graphics g, TextItemModel item, int x, int y) {
        g.drawString(item.getText(), x, y);
    }

    private void drawBitmapItem(Graphics g, BitmapItemModel item, int x, int y) {
        g.drawRect(x, y, 80, 60);
        g.drawString("Image: " + item.getImageName(), x, y + 75);
    }

    @Override
    public void update(Observable o, Object arg) {
        // When notified of a change in the model, repaint the component.
        repaint();
    }
}
