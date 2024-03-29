package controller;

import model.PresentationModel;
import model.DataAccessInterface;

public class FileController {
    private final PresentationModel presentationModel;
    private final DataAccessInterface dataAccess;

    public FileController(PresentationModel presentationModel, DataAccessInterface dataAccess) {
        this.presentationModel = presentationModel;
        this.dataAccess = dataAccess;
    }

    public void loadPresentation(String filePath) {
        try {
            PresentationModel loadedPresentation = dataAccess.loadPresentation(filePath);
            presentationModel.setTitle(loadedPresentation.getTitle());
            presentationModel.setSlides(loadedPresentation.getSlides());
            presentationModel.setCurrentSlideIndex(0);
            presentationModel.notifyObservers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void savePresentation(String filePath) {
        try {
            dataAccess.savePresentation(presentationModel, filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleLoadSaveException(Exception e, String message) {
        e.printStackTrace();
    }
}
