package View;

import controller.MainController;
import controller.MenuController;
import model.PresentationModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainView extends JFrame
{
    private PresentationModel presentationModel;
    private MainController mainController;
    private SlideView slideView;
    private final MenuController menuController;

    public MainView(PresentationModel presentationModel, MenuController menuController)
    {
        this.presentationModel = presentationModel;
        this.menuController = menuController;
        this.mainController = new MainController(presentationModel, this);
        initializeUI();
        mainController.initializeWithDemoPresentation();
        setupKeyboardControls();
    }

    private void initializeUI()
    {
        setTitle("Presentation Viewer");
        setSize(new Dimension(SlideView.WIDTH, SlideView.HEIGHT + 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        slideView = new SlideView(presentationModel);
        add(slideView, BorderLayout.CENTER);

        presentationModel.addObserver((o, arg) -> updateView());

        setupMenu();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupMenu()
    {
        MenuView menuView = new MenuView(menuController);
        this.setJMenuBar(menuView); // Set the menu bar
    }

    private void setupKeyboardControls()
    {
        bindKeyWithAction(KeyEvent.VK_PAGE_DOWN, 0, "nextSlide", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainController.nextSlide();
            }
        });
        bindKeyWithAction(KeyEvent.VK_DOWN, 0, "nextSlide", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainController.nextSlide();
            }
        });
        bindKeyWithAction(KeyEvent.VK_ENTER, 0, "nextSlide", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainController.nextSlide();
            }
        });
        bindKeyWithAction('+', 0, "nextSlide", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainController.nextSlide();
            }
        });

        bindKeyWithAction(KeyEvent.VK_PAGE_UP, 0, "previousSlide", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainController.previousSlide();
            }
        });
        bindKeyWithAction(KeyEvent.VK_UP, 0, "previousSlide", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainController.previousSlide();
            }
        });
        bindKeyWithAction('-', 0, "previousSlide", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                mainController.previousSlide();
            }
        });

        bindKeyWithAction('q', 0, "quit", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        bindKeyWithAction('Q', 0, "quit", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }

    private void bindKeyWithAction(int keyCode, int modifier, String actionMapKey, AbstractAction action)
    {
        JComponent component = (JComponent) getContentPane();
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(keyCode, modifier), actionMapKey);
        component.getActionMap().put(actionMapKey, action);
    }

    private void bindKeyWithAction(char character, int modifier, String actionMapKey, AbstractAction action)
    {
        JComponent component = (JComponent) getContentPane();
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(character, modifier), actionMapKey);
        component.getActionMap().put(actionMapKey, action);
    }



    public void setController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void updateView()
    {
        slideView.repaint();
        this.revalidate();
    }

    public void updateCurrentSlide()
    {
        slideView.repaint();
    }
}

