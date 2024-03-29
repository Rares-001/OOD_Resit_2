package View;


import controller.MenuController;
import model.XMLDataAccess;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuView extends JMenuBar {

    private MenuController menuController;

    public MenuView(MenuController menuController) {
        this.menuController = menuController;
        createMenu();
    }

    // Helper method to create and set up the menu
    private void createMenu() {
        // File menu
        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "Open", e -> {
            // Prompt the user to select a file
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                menuController.handleOpen(path);
            }
        });

        addMenuItem(fileMenu, "New", e -> menuController.handleNewAction());
        public void handleSaveAction() {
            try {
                XMLDataAccess xmlAccessor = new XMLDataAccess();
                xmlAccessor.save(presentationModel, SAVEFILE);
                mainView.displayMessage("Presentation saved successfully.");
            } catch (IOException e) {
                mainView.displayErrorMessage("Failed to save file: " + e.getMessage());
            }
        }

        addMenuItem(fileMenu, "Exit", e -> menuController.handleExitAction());
        this.add(fileMenu);

        // View menu
        JMenu viewMenu = new JMenu("View");
        addMenuItem(viewMenu, "Next Slide", e -> menuController.handleNextSlideAction());
        addMenuItem(viewMenu, "Previous Slide", e -> menuController.handlePreviousSlideAction());
        addMenuItem(viewMenu, "Go To", e -> menuController.handleGoToSlideAction());
        this.add(viewMenu);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        addMenuItem(helpMenu, "Search", e -> menuController.handleSearchAction());
        addMenuItem(helpMenu, "About", e -> menuController.handleAboutAction());
        this.add(helpMenu);
    }

    // Helper method to add menu items to a menu
    private void addMenuItem(JMenu menu, String title, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }
}
