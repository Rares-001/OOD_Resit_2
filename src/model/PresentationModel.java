package model;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class PresentationModel implements Observable {
    private String title;
    private List<SlideModel> slides;
    private int currentSlideIndex;
    private List<Observer> observers = new ArrayList<>();

    public PresentationModel() {
        slides = new ArrayList<>();
        currentSlideIndex = -1; // Start with no slide selected
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        // Notify observers about the title change
        notifyObservers();
    }

    public List<SlideModel> getSlides() {
        return slides;
    }

    public int getCurrentSlideIndex() {
        return currentSlideIndex;
    }

    public void setCurrentSlideIndex(int currentSlideIndex) {
        this.currentSlideIndex = currentSlideIndex;
        // Notify observers about slide index change
        notifyObservers();
    }

    // Observable interface methods
    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this, null);
        }
    }

    // The addSlide and removeSlide methods should notify observers after performing their actions.
    public void addSlide(SlideModel slide) {
        slides.add(slide);
        notifyObservers(); // Notify after adding a slide.
    }

    public void removeSlide(int index) {
        if (index >= 0 && index < slides.size()) {
            slides.remove(index);
            notifyObservers(); // Notify after removing a slide.
        }
    }

    // The nextSlide method should notify observers after changing the slide index.
    public void nextSlide() {
        if (currentSlideIndex < slides.size() - 1) {
            setCurrentSlideIndex(currentSlideIndex + 1);
            notifyObservers(); // Ensure observers are notified.
        }
    }

    // The previousSlide method should also notify observers.
    public void previousSlide() {
        if (currentSlideIndex > 0) {
            setCurrentSlideIndex(currentSlideIndex - 1);
            notifyObservers(); // Ensure observers are notified.
        }
    }

    // Method to get the current slide
    public SlideModel getCurrentSlide() {
        if (currentSlideIndex >= 0 && currentSlideIndex < slides.size()) {
            return slides.get(currentSlideIndex);
        }
        return null;
    }

    // Set the entire list of slides and notify observers
    public void setSlides(List<SlideModel> newSlides) {
        this.slides = newSlides;
        setCurrentSlideIndex(0); // Optionally reset the slide index
        notifyObservers(); // Notify observers of the change
    }
}
