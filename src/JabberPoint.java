import javax.swing.JOptionPane;
import controller.MainController;
import model.PresentationModel;
import View.MainView;
import model.Style;

public class JabberPoint {

	protected static final String IO_ERROR_MESSAGE = "IO Error: ";
	protected static final String JABBERPOINT_ERROR = "JabberPoint Error ";
	protected static final String JABBERPOINT_VERSION = "JabberPoint 1.6 - OU version";

	public static void main(String[] args) {
		try {
			Style.createStyles();

			PresentationModel presentationModel = new PresentationModel();

			MainView mainView = new MainView(presentationModel);

			MainController mainController = new MainController(presentationModel, mainView);

			mainView.setController(mainController);

			mainController.loadPresentation("testPresentation.xml");

			mainView.setVisible(true);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, IO_ERROR_MESSAGE + ex.getMessage(), JABBERPOINT_ERROR, JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}


}
