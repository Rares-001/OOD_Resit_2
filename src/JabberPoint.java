import javax.swing.JOptionPane;
import View.MainView;
import View.MenuView;
import controller.MainController;
import controller.MenuController;
import model.PresentationModel;
import model.Style;
import model.XMLDataAccess;

public class JabberPoint {

	public static void main(String[] args) {
		try {
			// System properties for macOS menu bar integration
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			System.setProperty("com.apple.mrj.application.apple.menu.about.name", "JabberPoint");

			Style.createStyles();
			PresentationModel presentationModel = new PresentationModel();
			XMLDataAccess dataAccess = new XMLDataAccess();

			MainView mainView = new MainView(presentationModel);
			MenuController menuController = new MenuController(presentationModel, mainView, dataAccess);
			MainController mainController = new MainController(presentationModel, mainView);

			MenuView menuView = new MenuView(menuController);
			mainView.setJMenuBar(menuView);

			mainView.setController(mainController);

			mainController.loadPresentation("testPresentation.xml");

			mainView.setVisible(true);

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "IO Error: " + ex.getMessage(), "JabberPoint Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
}
