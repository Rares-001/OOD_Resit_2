package model;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class PresentationModel implements Observable
{
    private String title;
    private List<SlideModel> slides;
    private int currentSlideIndex;
    private List<Observer> observers = new ArrayList<>();

    public PresentationModel()
    {
        slides = new ArrayList<>();
        currentSlideIndex = -1;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
        notifyObservers();
    }

    public List<SlideModel> getSlides()
    {
        return slides;
    }

    public int getCurrentSlideIndex()
    {
        return currentSlideIndex;
    }

    public void setCurrentSlideIndex(int currentSlideIndex)
    {
        this.currentSlideIndex = currentSlideIndex;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o)
    {
        observers.remove(o);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer o : observers)
        {
            o.update(this, null);
        }
    }

    public void addSlide(SlideModel slide)
    {
        slides.add(slide);
        notifyObservers();
    }

    public void nextSlide()
    {
        if (currentSlideIndex < slides.size() - 1)
        {
            setCurrentSlideIndex(currentSlideIndex + 1);
            notifyObservers();
        }
    }

    public void previousSlide()
    {
        if (currentSlideIndex > 0)
        {
            setCurrentSlideIndex(currentSlideIndex - 1);
            notifyObservers();
        }
    }

    public SlideModel getCurrentSlide()
    {
        if (currentSlideIndex >= 0 && currentSlideIndex < slides.size())
        {
            return slides.get(currentSlideIndex);
        }

        return null;
    }

    public void setSlides(List<SlideModel> newSlides)
    {
        this.slides = newSlides;
        notifyObservers();
    }
}
