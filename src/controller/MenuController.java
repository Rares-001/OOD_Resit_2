package controller;

import model.Accessor;
import model.Presentation;
import model.XMLAccessor;
import view.AboutBox;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
/**
 * MenuController handles actions triggered from the menu, updating the Presentation model accordingly.
 */
public class MenuController implements ActionListener {
	private Presentation presentation;

	public MenuController(Presentation presentation) {
		this.presentation = presentation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		switch (command) {
			case "Next":
				presentation.nextSlide();
				break;
			case "Previous":
				presentation.prevSlide();
				break;
			case "Open":
				openPresentation(); // This method would handle opening a new presentation
				break;
			case "Exit":
				System.exit(0);
				break;
		}
	}

	private void openPresentation() {
		// this implementation is empty
	}
}
