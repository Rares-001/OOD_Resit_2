package View;


import controller.MenuController;
import model.XMLDataAccess;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuView extends JMenuBar
{

    private MenuController menuController;

    public MenuView(MenuController menuController)
    {
        this.menuController = menuController;
        createMenu();
    }

    private void createMenu()
    {
        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "Open", e -> menuController.handleOpenAction(e));
        addMenuItem(fileMenu, "Save", e -> menuController.handleSaveAction(e));
        addMenuItem(fileMenu, "Exit", e -> menuController.handleExitAction(e));
        this.add(fileMenu);

        JMenu viewMenu = new JMenu("View");
        addMenuItem(viewMenu, "Next", e -> menuController.handleNextSlideAction(e));
        addMenuItem(viewMenu, "Previous", e -> menuController.handlePreviousSlideAction(e));
        addMenuItem(viewMenu, "Go to Slide", e -> menuController.handleGoToSlideAction(e));
        this.add(viewMenu);

        JMenu helpMenu = new JMenu("Help");
        addMenuItem(helpMenu, "About", e -> menuController.handleAboutAction(e));
        this.add(helpMenu);
    }

    private void addMenuItem(JMenu menu, String title, ActionListener actionListener)
    {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }
}
