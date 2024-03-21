package view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.KeyController;
import model.Presentation;
import controller.MenuController;

/**
 * <p>The applicatiewindow for a slideviewcomponent</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
*/

public class SlideViewerFrame extends JFrame {
	private Presentation presentation;
	private SlideViewerComponent slideViewerComponent;

	public SlideViewerFrame(String title, Presentation presentation) {
		super(title);
		this.presentation = presentation;

		// JFrame setup
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Initialize SlideViewerComponent
		slideViewerComponent = new SlideViewerComponent(presentation);
		add(slideViewerComponent);


		slideViewerComponent.setFocusable(true);
		slideViewerComponent.requestFocusInWindow();


		// Setup menu
		setupMenu();

		// Create and register the KeyController with the slideViewerComponent
		KeyController keyController = new KeyController(presentation);
		slideViewerComponent.addKeyListener(keyController); // Register KeyController

		setVisible(true);

	}

	private void setupMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem next = new JMenuItem("Next");
		next.addActionListener(e -> presentation.nextSlide());
		menu.add(next);

		JMenuItem prev = new JMenuItem("Previous");
		prev.addActionListener(e -> presentation.prevSlide());
		menu.add(prev);

		menuBar.add(menu);
		setJMenuBar(menuBar);
	}
}
