package View;

import controller.MainController;
import model.PresentationModel;
import model.SlideModel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private PresentationModel presentationModel;
    private MainController mainController;
    private SlideView slideView;

    public MainView(PresentationModel presentationModel) {
        this.presentationModel = presentationModel;
        this.mainController = new MainController(presentationModel, this);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Presentation Viewer");
        setSize(new Dimension(SlideView.WIDTH, SlideView.HEIGHT + 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        slideView = new SlideView(presentationModel.getCurrentSlide());
        add(slideView, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton prevButton = new JButton("< Previous");
        JButton nextButton = new JButton("Next >");

        prevButton.addActionListener(e -> mainController.previousSlide());
        nextButton.addActionListener(e -> mainController.nextSlide());

        controlPanel.add(prevButton);
        controlPanel.add(nextButton);
        add(controlPanel, BorderLayout.SOUTH);

        presentationModel.addObserver((o, arg) -> updateView());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    public void updateView() {
        slideView.setSlideModel(presentationModel.getCurrentSlide());
        slideView.repaint();
        this.revalidate();
    }


    public void setPresentationModel(PresentationModel model) {
        this.presentationModel = model;
        updateView();
    }

    public void updatePresentationView() {
        slideView.setSlideModel(presentationModel.getCurrentSlide());
        slideView.repaint();
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void updateCurrentSlide() {
        slideView.setSlideModel(presentationModel.getCurrentSlide());
        slideView.repaint();
    }
}

