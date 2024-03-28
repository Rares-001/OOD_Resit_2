import view.SlideViewerFrame;

import javax.swing.JOptionPane;
import java.io.IOException;

/** JabberPoint Main Program
 * <p>This program is distributed under the terms of the accompanying
 * COPYRIGHT.txt file (which is NOT the GNU General Public License).
 * Please read it. Your use of the software constitutes acceptance
 * of the terms in the COPYRIGHT.txt file.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class JabberPoint {
	protected static final String IO_ERROR_MESSAGE = "IO Error: ";
	protected static final String JABBERPOINT_ERROR = "JabberPoint Error ";
	protected static final String JABBERPOINT_VERSION = "JabberPoint 1.6 - OU version";

	/**
	 * The main program entry point.
	 */

	public static void main(String[] args) {
		// Initialize styles for the presentation
		Style.createStyles();

		// Create a new presentation
		Presentation presentation = new Presentation();

		// Initialize the main application window
		SlideViewerFrame viewerFrame = new SlideViewerFrame(JABBERPOINT_VERSION, presentation);

		try {
			// Load a demo or XML presentation based on the command-line argument
			loadPresentation(presentation, args);

			// Set the initial slide number
			presentation.setSlideNumber(0);
		} catch (Exception e) {
			// Show an error message dialog in case of failure
			JOptionPane.showMessageDialog(viewerFrame, IO_ERROR_MESSAGE + e.getMessage(), JABBERPOINT_ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Loads a presentation, either a demo or from an XML file, based on input arguments.
	 *
	 * @param presentation The presentation to load content into.
	 * @param args Command-line arguments passed to the application.
	 * @throws IOException If an error occurs during file loading.
	 */

	private static void loadPresentation(Presentation presentation, String[] args) throws IOException {
		if (args.length == 0) {
			// Load a demo presentation if no arguments are provided
			new DemoPresentation().loadFile(presentation, null);
		} else {
			// Load a presentation from the specified XML file
			new XMLAccessor().loadFile(presentation, args[0]);
		}
	}
}
