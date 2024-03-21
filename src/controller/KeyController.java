package controller;

import model.Presentation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/** <p>This is the controller.KeyController (KeyListener)</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

/**
 * KeyController manages keyboard input to control the Presentation model.
 */
public class KeyController extends KeyAdapter {
	private Presentation presentation;

	public KeyController(Presentation presentation) {
		this.presentation = presentation;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_PAGE_DOWN:
				presentation.nextSlide();
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_PAGE_UP:
				presentation.prevSlide();
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
		}
	}
}
