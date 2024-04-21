package view;

import model.*;
import observer.Observable;
import observer.Observer;
import model.PresentationModel;

import java.awt.*;
import javax.swing.JComponent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;

public class SlideView extends JComponent implements Observer, ImageObserver
{
    private final PresentationModel presentationModel;

    private static final Color BACKGROUND_COLOR = Color.white;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 800;
    private float scale = 1.0f;

    public SlideView(PresentationModel presentationModel)
    {
        this.presentationModel = presentationModel;
        this.setBackground(BACKGROUND_COLOR);
        setupComponentListener();
        presentationModel.addObserver(this);
    }

    private void setupComponentListener()
    {
        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                recalculateScale();
                repaint();
            }
        });
    }

    private void recalculateScale()
    {
        float widthScale = getWidth() / (float) WIDTH;
        float heightScale = getHeight() / (float) HEIGHT;
        scale = Math.min(widthScale, heightScale);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(WIDTH, HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawBackground(g);
        drawSlide(g);
        drawCounter(g);
    }

    private void drawBackground(Graphics g)
    {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawSlide(Graphics g)
    {
        SlideModel currentSlide = presentationModel.getCurrentSlide();
        if (currentSlide == null) return;

        int yPosition = 75;

        Style titleStyle = Style.getStyle(0);
        g.setFont(titleStyle.getFont(scale));
        g.setColor(titleStyle.getColor());
        g.drawString(currentSlide.getTitle(), 0, yPosition);
        yPosition += (int) (titleStyle.getLeading() * scale);

        for (SlideItemModel item : currentSlide.getItems())
        {
            if (item instanceof TextItemModel && ((TextItemModel) item).getLevel() != 0)
            {
                TextItemView textView = new TextItemView((TextItemModel) item);
                int itemHeight = textView.draw(g, 0, yPosition, scale, getWidth() - 100);
                yPosition += itemHeight + (int)(((TextItemModel) item).getStyle().getLeading() * scale);
            } else if (item instanceof BitmapItemModel)
            {
                BitmapItemView imageView = new BitmapItemView((BitmapItemModel) item);
                int itemHeight = imageView.draw(g, 50, yPosition, scale, this);
                yPosition += itemHeight + 10;
            }
        }
    }

    private void drawCounter(Graphics g) {
        int currentSlideNumber = presentationModel.getCurrentSlideIndex() + 1;
        int totalSlides = presentationModel.getSlides().size();
        String counterText = "Slide " + currentSlideNumber + " of " + totalSlides;
        Font counterFont = Style.getLabelFont();
        g.setFont(counterFont);
        g.setColor(Color.black);
        FontMetrics metrics = g.getFontMetrics(counterFont);
        int textWidth = metrics.stringWidth(counterText);
        int textHeight = metrics.getHeight();
        int paddingRight = 40;
        int paddingTop = 10;
        int x = getWidth() - textWidth - paddingRight;
        int y = paddingTop + textHeight;
        g.drawString(counterText, x, y);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        if (infoflags == ALLBITS) {
            repaint();
            return false;
        }

        return true;
    }
}
