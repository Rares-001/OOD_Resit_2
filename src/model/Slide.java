package model;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

/** <p>A slide. This class has drawing functionality.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

/**
 * Represents a single slide in the presentation, containing various slide items.
 * This class is designed to fit within an MVC framework, where it forms part of the Model.
 * It holds the data and logic for rendering a slide but delegates the actual rendering to the view.
 */

public class Slide {
	private String title;
	private List<SlideItem> items;

	public Slide() {
		this.items = new ArrayList<>();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void addItem(SlideItem item) {
		items.add(item);
	}

	public void append(SlideItem item) {
		this.items.add(item);
	}

	public List<SlideItem> getItems() {
		return new ArrayList<>(items);
	}

	/**
	 * Facilitates the rendering of the slide. This method organizes slide items
	 * for rendering and is called by the view (SlideViewerComponent).
	 *
	 * @param g The Graphics context used for drawing.
	 * @param area The area on the screen where the slide should be drawn.
	 * @param observer The image observer to be notified of image updates.
	 */

	public void draw(Graphics g, Rectangle area, ImageObserver observer) {
		int y = area.y;
		for (SlideItem item : items) {
			item.draw(g, area.x, y, observer);
			y += item.getHeight(g, observer);
		}
	}
}
