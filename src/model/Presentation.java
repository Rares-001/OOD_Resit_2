package model;

import java.util.ArrayList;


/**
 * <p>Presentations keeps track of the slides in a presentation.</p>
 * <p>Only one instance of this class is available.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */


/**
 * Manages the slides and the current state of the presentation.
 */

public class Presentation {
	private String title;
	private ArrayList<Slide> slides;
	private int currentSlideIndex;

	public Presentation() {
		this.slides = new ArrayList<>();
		this.currentSlideIndex = 0; // Start with the first slide
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// Adds a slide to the presentation
	public void addSlide(Slide slide) {
		slides.add(slide);
	}

	// Returns the slide at the specified index
	public Slide getSlide(int index) {
		if (index >= 0 && index < slides.size()) {
			return slides.get(index);
		}
		return null; // Return null if the index is out of boundss
	}

	// returns the number of slides in the presentation
	public int getSize() {
		return slides.size();
	}

	// Advances to the next slide, if possible
	public void nextSlide() {
		if (currentSlideIndex < slides.size() - 1) {
			currentSlideIndex++;
		}
	}

	// Moves back to the previous slide, if possible
	public void prevSlide() {
		if (currentSlideIndex > 0) {
			currentSlideIndex--;
		}
	}

	// Sets the current slide to the specified index
	public void setSlideNumber(int number) {
		if (number >= 0 && number < slides.size()) {
			currentSlideIndex = number;
		}
	}

	// Returns the index of the current slide
	public int getSlideNumber() {
		return currentSlideIndex;
	}

	// Returns the current slide being viewed
	public Slide getCurrentSlide() {
		return getSlide(currentSlideIndex);
	}

	// Clears all slides from the presentation
	public void clear() {
		slides.clear();
		currentSlideIndex = 0;
	}
}
