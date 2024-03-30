package controller;

import controller.MainController;
import model.PresentationModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.PresentationModel;
import model.DataAccessInterface;
import View.MainView;

public class MenuController {
    private PresentationModel presentationModel;
    private MainView mainView;
    private DataAccessInterface dataAccess;

    public MenuController(PresentationModel presentationModel, MainView mainView, DataAccessInterface dataAccess) {
        this.presentationModel = presentationModel;
        this.mainView = mainView;
        this.dataAccess = dataAccess;
    }

    public void handleOpenAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(mainView);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                PresentationModel newPresentation = dataAccess.loadPresentation(path);
                presentationModel.setTitle(newPresentation.getTitle());
                presentationModel.setSlides(newPresentation.getSlides());
                mainView.updatePresentationView();
            } catch (IOException ex) {
                mainView.displayErrorMessage("Failed to open file: " + ex.getMessage());
            }
        }
    }

    public void handleSaveAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(mainView);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                dataAccess.savePresentation(presentationModel, path);
                mainView.displayMessage("Presentation saved successfully.");
            } catch (IOException ex) {
                mainView.displayErrorMessage("Failed to save file: " + ex.getMessage());
            }
        }
    }

    public void handleExitAction(ActionEvent e) {
        System.exit(0);
    }

    public void handleNextSlideAction(ActionEvent e) {
        presentationModel.nextSlide();
        mainView.updateCurrentSlide();
    }

    public void handlePreviousSlideAction(ActionEvent e) {
        presentationModel.previousSlide();
        mainView.updateCurrentSlide();
    }

    public void handleGoToSlideAction(ActionEvent e) {
        String slideNumberStr = JOptionPane.showInputDialog(mainView, "Enter slide number:");
        try {
            int slideNumber = Integer.parseInt(slideNumberStr);
            if (slideNumber >= 1 && slideNumber <= presentationModel.getSlides().size()) {
                presentationModel.setCurrentSlideIndex(slideNumber - 1);
                mainView.updateCurrentSlide();
            } else {
                JOptionPane.showMessageDialog(mainView, "Slide number out of range.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) { // Changed variable name to avoid conflict
            JOptionPane.showMessageDialog(mainView, "Invalid slide number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleAboutAction(ActionEvent e) {
        JOptionPane.showMessageDialog(mainView, "About Information", "About", JOptionPane.INFORMATION_MESSAGE);
    }

}
