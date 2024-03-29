package View;

import controller.MainController;
import model.PresentationModel;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private PresentationModel presentationModel;
    private MainController mainController;
    private SlideView slideView; // Component for displaying the current slide

    public MainView(PresentationModel presentationModel, MainController mainController) {
        this.presentationModel = presentationModel;
        this.mainController = mainController;
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

        // Ensuring the view listens for changes in the presentation model to update the display
        presentationModel.addObserver((o, arg) -> updateView());
        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    public void updateView() {
        // Update slideView with the current slide from the presentation model
        slideView.setSlideModel(presentationModel.getCurrentSlide());
        slideView.repaint();
    }

    // Method to update the entire presentation view
    public void updatePresentationView() {
        // This could involve refreshing the slideView and other components if needed.
        slideView.setSlideModel(presentationModel.getCurrentSlide());
        slideView.repaint();
    }

    // Method to display an error message
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Method to display a confirmation or information message
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to update only the current slide view
    public void updateCurrentSlide() {
        slideView.setSlideModel(presentationModel.getCurrentSlide());
        slideView.repaint();
    }
}

