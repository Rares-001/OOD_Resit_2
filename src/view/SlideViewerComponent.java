package view;

import model.Presentation;
import model.Slide;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JFrame;


/** <p>view.SlideViewerComponent is a graphical component that ca display Slides.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent {
	private Presentation presentation;

	public SlideViewerComponent(Presentation presentation) {
		this.presentation = presentation;
		setBackground(Color.white);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Slide currentSlide = presentation.getCurrentSlide();
		if (currentSlide != null) {
			drawSlide(g, currentSlide);
		}
	}

	/**
	 * Draws the current slide, including all its items.
	 *
	 * @param g Graphics context
	 * @param slide The slide to draw
	 */
	private void drawSlide(Graphics g, Slide slide) {
		// Background
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		// Set up for drawing slide items
		Rectangle area = new Rectangle(0, 0, getWidth(), getHeight());
		slide.draw(g, area, this); // Delegate drawing to the Slide, which knows how to draw itself and its items
	}
}
