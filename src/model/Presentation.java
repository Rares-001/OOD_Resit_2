package model;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a presentation, which is a collection of slides.
 * This class is observable, meaning it notifies registered observers about changes to its state.
 */

public class Presentation implements Observable {
	private List<Slide> slides;
	private int currentSlideIndex = 0;

	// The list of observers to be notified about changes in the presentation.
	private List<Observer> observers = new ArrayList<>();

	public Presentation() {
		this.slides = new ArrayList<>();
	}
	public void append(Slide slide) {
		slides.add(slide);
		notifyObservers(); // Notify observers of the change if implementing Observable.
	}

	public void setTitle(String title) {
		// Set the title of the presentation and notify observers of change.
		notifyObservers();
	}

	public void addSlide(Slide slide) {
		slides.add(slide);
		notifyObservers();
	}

	public void removeSlide(Slide slide) {
		slides.remove(slide);
		notifyObservers();
	}

	public void nextSlide() {
		if (currentSlideIndex < slides.size() - 1) {
			currentSlideIndex++;
			notifyObservers();
		}
	}

	public void prevSlide() {
		if (currentSlideIndex > 0) {
			currentSlideIndex--;
			notifyObservers();
		}
	}

	public Slide getCurrentSlide() {
		if (slides.isEmpty()) {
			return null; // Or a default slide.
		}
		return slides.get(currentSlideIndex);
	}

	public int getCurrentSlideNumber() {
		return currentSlideIndex;
	}

	public void setSlideNumber(int slideNumber) {
		if (slideNumber >= 0 && slideNumber < slides.size()) {
			currentSlideIndex = slideNumber;
			notifyObservers();
		}
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}
