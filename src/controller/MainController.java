package controller;

import View.MainView;
import model.*;

public class MainController
{
    private final PresentationModel presentationModel;
    private final MainView mainView;

    public MainController(PresentationModel presentationModel, MainView mainView)
    {
        this.presentationModel = presentationModel;
        this.mainView = mainView;
    }

    public void startPresentation()
    {
        presentationModel.setCurrentSlideIndex(0);
        mainView.updateView();
    }

    public void nextSlide()
    {
        if (presentationModel.getCurrentSlideIndex() < presentationModel.getSlides().size() - 1)
        {
            presentationModel.setCurrentSlideIndex(presentationModel.getCurrentSlideIndex() + 1);
        }
    }

    public void previousSlide()
    {
        if (presentationModel.getCurrentSlideIndex() > 0)
        {
            presentationModel.setCurrentSlideIndex(presentationModel.getCurrentSlideIndex() - 1);
        }
    }

    public void initializeWithDemoPresentation() {
        loadDemoPresentationAsDefault();
    }

    public void loadDemoPresentationAsDefault()
    {
        presentationModel.getSlides().clear();
        presentationModel.setTitle("Demo Presentation");

        Style.createStyles();

        SlideModel slide1 = new SlideModel();
        slide1.setTitle("JabberPoint");
        slide1.addItem(new TextItemModel(1, "The Java presentation tool", Style.getStyle(1)));
        slide1.addItem(new TextItemModel(2, "Copyright (c) 1996-2000: Ian Darwin", Style.getStyle(2)));
        slide1.addItem(new TextItemModel(2, "Copyright (c) 2000-now:", Style.getStyle(2)));
        slide1.addItem(new TextItemModel(2, "Gert Florijn and Sylvia Stuurman", Style.getStyle(2)));
        slide1.addItem(new TextItemModel(4, "Calling Jabberpoint without a filename", Style.getStyle(4)));
        slide1.addItem(new TextItemModel(4, "will show this presentation", Style.getStyle(4)));
        slide1.addItem(new TextItemModel(1, "Navigate:", Style.getStyle(1)));
        slide1.addItem(new TextItemModel(3, "Next slide: PgDn or Enter", Style.getStyle(3)));
        slide1.addItem(new TextItemModel(3, "Previous slide: PgUp or up-arrow", Style.getStyle(3)));
        slide1.addItem(new TextItemModel(3, "Quit: q or Q", Style.getStyle(3)));
        presentationModel.addSlide(slide1);

        SlideModel slide2 = new SlideModel();
        slide2.setTitle("Demonstration of levels and styles");
        slide2.addItem(new TextItemModel(1, "Level 1", Style.getStyle(1)));
        slide2.addItem(new TextItemModel(2, "Level 2", Style.getStyle(2)));
        slide2.addItem(new TextItemModel(1, "Again level 1", Style.getStyle(1)));
        slide2.addItem(new TextItemModel(1, "Level 1 has style number 1", Style.getStyle(1)));
        slide2.addItem(new TextItemModel(2, "Level 2 has style number 2", Style.getStyle(2)));
        slide2.addItem(new TextItemModel(3, "This is how level 3 looks like", Style.getStyle(3)));
        slide2.addItem(new TextItemModel(4, "And this is level 4", Style.getStyle(4)));
        presentationModel.addSlide(slide2);

        SlideModel slide3 = new SlideModel();
        slide3.setTitle("The third slide");
        slide3.addItem(new TextItemModel(1, "To open a new presentation,", Style.getStyle(1)));
        slide3.addItem(new TextItemModel(2, "use File->Open from the menu.", Style.getStyle(2)));
        slide3.addItem(new TextItemModel(1, " ", Style.getStyle(1)));
        slide3.addItem(new TextItemModel(1, "This is the end of the presentation.", Style.getStyle(1)));
        slide3.addItem(new BitmapItemModel(1, "JabberPoint.jpg"));
        presentationModel.addSlide(slide3);

        startPresentation();
    }
}
