package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Presentation;
import model.XMLAccessor;

/**
 * Handles menu actions for the JabberPoint application.
 */

public class MenuController implements ActionListener {
	private Presentation presentation;

	/**
	 * Constructs a MenuController for the specified presentation.
	 *
	 * @param presentation The presentation to be manipulated by menu actions.
	 */

	public MenuController(Presentation presentation) {
		this.presentation = presentation;
	}

	/**
	 * Responds to menu item actions
	 *
	 * @param e The action event triggered by selecting a menu item.
	 */

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
				openPresentation();
				break;
			case "Exit":
				System.exit(0);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Unrecognized menu command: " + command, "Error", JOptionPane.ERROR_MESSAGE);
				break;
		}
	}

	/**
	 * Handles opening a new presentation file.
	 */

	private void openPresentation() {
		String filePath = JOptionPane.showInputDialog(null, "Enter the path of the presentation file:");

		if (filePath != null && !filePath.trim().isEmpty()) {
			try {
				new XMLAccessor().loadFile(presentation, filePath);
				presentation.setSlideNumber(0); // Start at the beginning of the new presentation
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Failed to load presentation from: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
