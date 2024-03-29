package controller;

import View.MainView;
import model.PresentationModel;

public class MainController {
    private PresentationModel presentationModel;
    private MainView mainView;

    public MainController(PresentationModel presentationModel, MainView mainView) {
        this.presentationModel = presentationModel;
        this.mainView = mainView;
    }

    public void startPresentation() {
        presentationModel.setCurrentSlideIndex(0);
        mainView.updateView();
    }

    public void nextSlide() {
        if (presentationModel.getCurrentSlideIndex() < presentationModel.getSlides().size() - 1) {
            presentationModel.setCurrentSlideIndex(presentationModel.getCurrentSlideIndex() + 1);
            mainView.updateView();
        }
    }

    public void previousSlide() {
        if (presentationModel.getCurrentSlideIndex() > 0) {
            presentationModel.setCurrentSlideIndex(presentationModel.getCurrentSlideIndex() - 1);
            mainView.updateView();
        }
    }
}

