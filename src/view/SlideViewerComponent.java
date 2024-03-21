package view;

import model.Presentation;
import model.Slide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;

import model.SlideItem;
import observer.Observer;


/** <p>view.SlideViewerComponent is a graphical component that ca display Slides.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

/**
 * SlideViewerComponent acts as the View in the MVC architecture, responsible for rendering the current slide.
 * It observes the Presentation model for changes and updates the display accordingly.
 */

public class SlideViewerComponent extends JComponent implements Observer {
	private Presentation presentation;

	public SlideViewerComponent(Presentation presentation) {
		this.presentation = presentation;
		this.presentation.addObserver(this); // Registers itself as an observer to the presentation
		setFocusable(true); // Make the component focusable
	}

	@Override
	public void update() {
		repaint(); // Triggered when the observed Presentation model changes
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Ensure there's a presentation and a current slide to draw
		if (presentation != null && presentation.getCurrentSlide() != null) {
			drawSlide(g, presentation.getCurrentSlide());
		}
	}

	/**
	 * Draws the current slide.
	 *
	 * @param g Graphics context used for drawing.
	 * @param slide The slide to draw.
	 */

	private void drawSlide(Graphics g, Slide slide) {
		super.paintComponent(g);
		setBackground(Color.white);

		// Drawing the slide title
		g.drawString("Slide Title: " + slide.getTitle(), 10, 20);

		// Correctly iterate over the slide items using the getItems() method
		List<SlideItem> items = slide.getItems();
		int y = 50; // Starting y position for items
		for (SlideItem item : items) {
			// Assume each item knows how to draw itself.
			// The draw method for each SlideItem needs to be implemented to handle this.
			item.draw(g, 50, y, this);
			y += item.getHeight(g, this) + 20; // Adjust the y position for the next item
		}
	}

}
