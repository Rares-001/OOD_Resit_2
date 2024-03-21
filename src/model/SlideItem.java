package model;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

/** <p>The abstract class for items on a slide.<p>
 * <p>All SlideItems have drawing capabilities.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

/**
 * The abstract class for items on a slide. All SlideItems have drawing capabilities.
 */

public abstract class SlideItem {
	private int level;

	public SlideItem(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	// The draw method doesn't need to notify observers since it's
	// about rendering, not changing the state.
	public abstract void draw(Graphics g, int x, int y, ImageObserver observer);

	// Similarly, getHeight is about rendering, not state change.
	public abstract int getHeight(Graphics g, ImageObserver observer);
}
