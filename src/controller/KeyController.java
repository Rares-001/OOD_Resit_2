package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Presentation;

/**
 * Handles keyboard input for navigating through a presentation.
 */

public class KeyController extends KeyAdapter {
	private Presentation presentation;

	/**
	 * Constructs a KeyController for a given presentation.
	 *
	 * @param presentation The presentation to navigate.
	 */

	public KeyController(Presentation presentation) {
		this.presentation = presentation;
	}

	/**
	 * Responds to key pressed events to navigate the presentation.
	 *
	 * @param e The KeyEvent triggered by pressing a key.
	 */

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
			case KeyEvent.VK_Q:
				System.exit(0); // Quits the application
				break;
		}
	}
}
