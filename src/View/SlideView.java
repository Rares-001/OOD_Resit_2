package View;

import model.*;
import observer.Observable;
import observer.Observer;

import java.awt.*;
import javax.swing.JComponent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SlideView extends JComponent implements Observer {
    private SlideModel slideModel;
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color TEXT_COLOR = Color.black;
    private static final String FONT_NAME = "Dialog";
    private static final int FONT_STYLE = Font.BOLD;
    private static final int FONT_SIZE = 10;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private float scale = 1.0f;

    public SlideView(SlideModel slideModel) {
        super();
        this.setBackground(BACKGROUND_COLOR);
        setSlideModel(slideModel);
        setupComponentListener();
    }

    private void setupComponentListener() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                recalculateScale();
                repaint();
            }
        });
    }

    public void setSlideModel(SlideModel newSlideModel) {
        this.slideModel = newSlideModel;
        if (this.slideModel != null) {
            this.slideModel.addObserver(this);
        }
        repaint();
    }

    private void recalculateScale() {
        float widthScale = getWidth() / (float) WIDTH;
        float heightScale = getHeight() / (float) HEIGHT;
        scale = Math.min(widthScale, heightScale);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);
        if (slideModel != null) {
            drawSlide(g);
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawSlide(Graphics g) {
        int yPosition = (int) (50 * scale);

        Style titleStyle = Style.getStyle(1);
        g.setFont(titleStyle.getFont(scale));
        g.setColor(titleStyle.getColor());
        g.drawString(slideModel.getTitle(), (int) (50 * scale), yPosition);

        yPosition += g.getFontMetrics().getHeight() + (int) (10 * scale);

        for (SlideItemModel item : slideModel.getItems()) {
            if (item instanceof TextItemModel) {
                TextItemView textView = new TextItemView((TextItemModel) item);
                int itemHeight = textView.draw(g, (int) (50 * scale), yPosition, scale, getWidth() - (int) (100 * scale));
                yPosition += itemHeight + (int) (10 * scale);
            } else if (item instanceof BitmapItemModel) {
            }
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
