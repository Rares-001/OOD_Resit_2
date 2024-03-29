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

    private void createMenu() {
        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "Open", e -> menuController.handleOpen());
        addMenuItem(fileMenu, "Save", e -> menuController.handleSave());
        addMenuItem(fileMenu, "Exit", e -> menuController.handleExit());
        this.add(fileMenu);
    }


    private void addMenuItem(JMenu menu, String title, ActionListener actionListener) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }
}
