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

    public void handleOpen() {
        handleOpenAction();
    }

    private void handleOpenAction() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(mainView);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                PresentationModel newPresentation = dataAccess.loadPresentation(path);
                presentationModel.setTitle(newPresentation.getTitle());
                presentationModel.setSlides(newPresentation.getSlides());
                mainView.updatePresentationView();
            } catch (IOException e) {
                mainView.displayErrorMessage("Failed to open file: " + e.getMessage());
            }
        }
    }

    public void handleSave() {
        handleSaveAction();
    }

    private void handleSaveAction() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(mainView);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                dataAccess.savePresentation(presentationModel, path);
                mainView.displayMessage("Presentation saved successfully.");
            } catch (IOException e) {
                mainView.displayErrorMessage("Failed to save file: " + e.getMessage());
            }
        }
    }

    public void handleExit() {
        handleExitAction();
    }

    private void handleExitAction() {
        System.exit(0);
    }

    public void handleNextSlideAction() {
        presentationModel.nextSlide();
        mainView.updateCurrentSlide();
    }

    public void handlePreviousSlideAction() {
        presentationModel.previousSlide();
        mainView.updateCurrentSlide();
    }

    private void handleGoToSlideAction() {
        String slideNumberStr = JOptionPane.showInputDialog(mainView, "Enter slide number:");
        try {
            int slideNumber = Integer.parseInt(slideNumberStr);
            if (slideNumber >= 1 && slideNumber <= presentationModel.getSlides().size()) {
                presentationModel.setCurrentSlideIndex(slideNumber - 1);
                mainView.updateCurrentSlide();
            } else {
                JOptionPane.showMessageDialog(mainView, "Slide number out of range.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainView, "Invalid slide number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAboutAction() {
        JOptionPane.showMessageDialog(mainView, "About Information", "About", JOptionPane.INFORMATION_MESSAGE);
    }

}
