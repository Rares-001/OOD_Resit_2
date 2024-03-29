package controller;

import controller.MainController;
import model.PresentationModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.PresentationModel;
import model.DataAccessInterface;
import View.MainView;

public class MenuController {
    private PresentationModel presentationModel;
    private MainView mainView;
    private DataAccessInterface dataAccess;

    public MenuController(PresentationModel presentationModel, MainView mainView) {
        this.presentationModel = presentationModel;
        this.mainView = mainView;
        initializeMenu();
    }

    private void initializeMenu() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOpenAction();
            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSaveAction();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExitAction();
            }
        });

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAboutAction();
            }
        });

        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        mainView.setJMenuBar(menuBar);
    }

    public void handleOpen(String path) {
        handleOpenAction(path);
    }

    private void handleOpenAction(String path) {
        try {
            PresentationModel newPresentation = dataAccess.loadPresentation(path);
            presentationModel.setTitle(newPresentation.getTitle());
            presentationModel.setSlides(newPresentation.getSlides());
            mainView.updatePresentationView(); // Refresh the entire presentation view
        } catch (Exception e) {
            mainView.displayErrorMessage("Failed to open file: " + e.getMessage());
        }
    }

    public void handleSaveAction(String path) {
        try {
            dataAccess.savePresentation(presentationModel, path);
            mainView.displayMessage("Presentation saved successfully.");
        } catch (Exception e) {
            mainView.displayErrorMessage("Failed to save file: " + e.getMessage());
        }
    }

    public void handleNextSlideAction() {
        presentationModel.nextSlide();
        mainView.updateCurrentSlide(); // Refresh to show the current slide
    }

    public void handlePreviousSlideAction() {
        presentationModel.previousSlide();
        mainView.updateCurrentSlide(); // Refresh to show the current slide
    }

    public void handleRemoveSlideAction() {
        presentationModel.removeSlide(presentationModel.getCurrentSlideIndex());;
        // missing mainView.
    }

    public void handleNewAction() {
    }

    private void handleAboutAction() {

    }

    private void handleExitAction() {
    }
}
