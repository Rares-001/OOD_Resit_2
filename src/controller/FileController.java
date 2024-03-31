package controller;

import model.PresentationModel;
import model.DataAccessInterface;

import javax.swing.*;

public class FileController
{
    private final PresentationModel presentationModel;
    private final DataAccessInterface dataAccess;

    public FileController(PresentationModel presentationModel, DataAccessInterface dataAccess)
    {
        this.presentationModel = presentationModel;
        this.dataAccess = dataAccess;
    }

    public void loadPresentation(String filePath)
    {
        try {
            PresentationModel loadedPresentation = dataAccess.loadPresentation(filePath);
            presentationModel.setTitle(loadedPresentation.getTitle());
            presentationModel.setSlides(loadedPresentation.getSlides());
            presentationModel.setCurrentSlideIndex(0);
            presentationModel.notifyObservers();
        } catch (Exception e)
        {
            handleLoadSaveException(e, "Failed to load presentation from file: " + filePath);
        }
    }

    public void savePresentation(String filePath)
    {
        try {
            dataAccess.savePresentation(presentationModel, filePath);
        } catch (Exception e)
        {
            handleLoadSaveException(e, "Failed to save presentation to file: " + filePath);
        }
    }

    // abstracts the error handling logic for load and save operations
    private void handleLoadSaveException(Exception e, String message)
    {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
