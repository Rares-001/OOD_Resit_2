package view;


import controller.MenuController;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuView extends JMenuBar
{

    private final MenuController menuController;

    public MenuView(MenuController menuController)
    {
        this.menuController = menuController;
        createMenu();
    }

    private void createMenu()
    {
        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "Open", e -> menuController.handleOpenAction());
        addMenuItem(fileMenu, "Save", e -> menuController.handleSaveAction());
        addMenuItem(fileMenu, "Exit", e -> menuController.handleExitAction());
        this.add(fileMenu);

        JMenu viewMenu = new JMenu("view");
        addMenuItem(viewMenu, "Next", e -> menuController.handleNextSlideAction());
        addMenuItem(viewMenu, "Previous", e -> menuController.handlePreviousSlideAction());
        addMenuItem(viewMenu, "Go to Slide", e -> menuController.handleGoToSlideAction());
        this.add(viewMenu);

        JMenu helpMenu = new JMenu("Help");
        addMenuItem(helpMenu, "About", e -> menuController.handleAboutAction());
        this.add(helpMenu);
    }

    private void addMenuItem(JMenu menu, String title, ActionListener actionListener)
    {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }
}
