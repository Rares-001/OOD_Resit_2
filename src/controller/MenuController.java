package controller;

import view.AboutBoxView;
import view.MainView;
import model.PresentationModel;
import javax.swing.*;

public class MenuController
{
    private final PresentationModel presentationModel;
    private final MainView mainView;
    private final FileController fileController;

    public MenuController(PresentationModel presentationModel, MainView mainView, FileController fileController)
    {
        this.presentationModel = presentationModel;
        this.mainView = mainView;
        this.fileController = fileController;
    }

    public void handleOpenAction()
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(mainView);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            fileController.loadPresentation(path);
        }
    }

    public void handleSaveAction()
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(mainView);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            fileController.savePresentation(path);
        }
    }

    public void handleExitAction()
    {
        System.exit(0);
    }

    public void handleNextSlideAction()
    {
        presentationModel.nextSlide();
        mainView.updateCurrentSlide();
    }

    public void handlePreviousSlideAction()
    {
        presentationModel.previousSlide();
        mainView.updateCurrentSlide();
    }

    public void handleGoToSlideAction()
    {
        String slideNumberStr = JOptionPane.showInputDialog(mainView, "Enter slide number:");
        try
        {
            int slideNumber = Integer.parseInt(slideNumberStr);
            if (slideNumber >= 1 && slideNumber <= presentationModel.getSlides().size())
            {
                presentationModel.setCurrentSlideIndex(slideNumber - 1);
                mainView.updateCurrentSlide();
            } else
            {
                JOptionPane.showMessageDialog(mainView, "Slide number out of range.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainView, "Invalid slide number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleAboutAction()
    {
        AboutBoxView.showDialog(mainView);
    }

}
