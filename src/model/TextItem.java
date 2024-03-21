package model;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

/** <p>A text item.</p>
 * <p>A text item has drawing capabilities.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

/**
 * A text item on a slide.
 */
public class TextItem extends SlideItem {
	private String text;
	private Font font;

	public TextItem(int level, String text) {
		super(level);
		this.text = text;
		this.font = new Font("Arial", Font.PLAIN, 24);
	}

	@Override
	public void draw(Graphics g, int x, int y, ImageObserver observer) {
		g.setFont(font);
		g.drawString(text, x, y);
	}

	@Override
	public int getHeight(Graphics g, ImageObserver observer) {
		return g.getFontMetrics(font).getHeight();
	}
}
