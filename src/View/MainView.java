package View;

import controller.MainController;
import model.PresentationModel;
import model.SlideModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainView extends JFrame {
    private PresentationModel presentationModel;
    private MainController mainController;
    private SlideView slideView;

    public MainView(PresentationModel presentationModel) {
        this.presentationModel = presentationModel;
        this.mainController = new MainController(presentationModel, this);
        initializeUI();
        setupKeyboardControls();
    }

    private void initializeUI() {
        setTitle("Presentation Viewer");
        setSize(new Dimension(SlideView.WIDTH, SlideView.HEIGHT + 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        slideView = new SlideView(presentationModel.getCurrentSlide());
        add(slideView, BorderLayout.CENTER);

        presentationModel.addObserver((o, arg) -> updateView());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupKeyboardControls() {
        bindKeyWithAction(KeyEvent.VK_PAGE_DOWN, 0, "nextSlide", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.nextSlide();
            }
        });
        bindKeyWithAction(KeyEvent.VK_DOWN, 0, "nextSlide", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.nextSlide();
            }
        });
        bindKeyWithAction(KeyEvent.VK_ENTER, 0, "nextSlide", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.nextSlide();
            }
        });
        bindKeyWithAction('+', 0, "nextSlide", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.nextSlide();
            }
        });

        bindKeyWithAction(KeyEvent.VK_PAGE_UP, 0, "previousSlide", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.previousSlide();
            }
        });
        bindKeyWithAction(KeyEvent.VK_UP, 0, "previousSlide", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.previousSlide();
            }
        });
        bindKeyWithAction('-', 0, "previousSlide", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.previousSlide();
            }
        });

        bindKeyWithAction('q', 0, "quit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bindKeyWithAction('Q', 0, "quit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void bindKeyWithAction(int keyCode, int modifier, String actionMapKey, AbstractAction action) {
        JComponent component = (JComponent) getContentPane();
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, modifier), actionMapKey);
        component.getActionMap().put(actionMapKey, action);
    }

    private void bindKeyWithAction(char character, int modifier, String actionMapKey, AbstractAction action) {
        JComponent component = (JComponent) getContentPane();
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(character, modifier), actionMapKey);
        component.getActionMap().put(actionMapKey, action);
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

