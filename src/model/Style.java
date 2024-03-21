package model;

import java.awt.Color;
import java.awt.Font;

/** <p>model.Style stands for Indent, Color, Font and Leading.</p>
 * <p>The link between a style number and a item level is hard-linked:
 * in model.Slide the style is grabbed for an item
 * with a style number the same as the item level.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Style {
	private static Style[] styles;

	private static final String FONT_NAME = "Helvetica";
	private int indent;
	private Color color;
	private Font font;
	private int fontSize;
	private int leading;

	// Initialize default styles
	public static void createStyles() {
		styles = new Style[] {
				new Style(0, Color.red, 48, 20),
				new Style(20, Color.blue, 40, 10),
				new Style(50, Color.black, 36, 10),
				new Style(70, Color.black, 30, 10),
				new Style(90, Color.black, 24, 10)
		};
	}

	// Retrieve style by level
	public static Style getStyle(int level) {
		if (level < styles.length) {
			return styles[level];
		}
		return styles[styles.length - 1];
	}

	private Style(int indent, Color color, int fontSize, int leading) {
		this.indent = indent;
		this.color = color;
		this.fontSize = fontSize; // Ensure fontSize is set before creating the font
		this.font = new Font(FONT_NAME, Font.BOLD, this.fontSize);
		this.leading = leading;
	}

	public int getIndent() {
		return indent;
	}

	public Color getColor() {
		return color;
	}

	public Font getFont() {
		return font;
	}

	public int getFontSize() {
		return fontSize;
	}

	public int getLeading() {
		return leading;
	}
}
