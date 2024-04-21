package controller;

import model.PresentationModel;
import model.DataAccessInterface;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileController
{
    private final PresentationModel presentationModel;
    private final DataAccessInterface dataAccess;

    public FileController(PresentationModel presentationModel, DataAccessInterface dataAccess)
    {
        this.presentationModel = presentationModel;
        this.dataAccess = dataAccess;
    }

    public void loadPresentation(String filePath) {
        try {
            PresentationModel loadedPresentation = dataAccess.loadPresentation(filePath);
            updatePresentationModel(loadedPresentation);
        } catch (FileNotFoundException fnfe) {
            handleLoadSaveException(fnfe, "Presentation file not found: " + filePath);
        } catch (IOException ioe) {
            handleLoadSaveException(ioe, "Error reading from file: " + filePath);
        } catch (Exception e) {
            handleLoadSaveException(e, "Unexpected error loading presentation from file: " + filePath);
        }
    }

    private void updatePresentationModel(PresentationModel loadedPresentation) {
        presentationModel.setTitle(loadedPresentation.getTitle());
        presentationModel.setSlides(loadedPresentation.getSlides());
        presentationModel.setCurrentSlideIndex(0);
        presentationModel.notifyObservers();
    }

    public void savePresentation(String filePath) {
        try {
            dataAccess.savePresentation(presentationModel, filePath);
        } catch (FileNotFoundException fnfe) {
            handleLoadSaveException(fnfe, "Cannot save to file. File not found: " + filePath);
        } catch (IOException ioe) {
            handleLoadSaveException(ioe, "Error writing to file: " + filePath);
        } catch (Exception e) {
            handleLoadSaveException(e, "Unexpected error saving presentation to file: " + filePath);
        }
    }

    // abstracts the error handling logic for load and save operations
    private void handleLoadSaveException(Exception e, String message)
    {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
