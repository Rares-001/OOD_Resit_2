package model;

/**
 * A built-in demo presentation that can be loaded without an external file.
 */

public class DemoPresentation extends Accessor {

	/**
	 * Loads a demo presentation with predefined slides and items into the given Presentation object.
	 *
	 * @param presentation The presentation object to load data into.
	 * @param unusedFilename A placeholder parameter to maintain consistency with the Accessor interface. Not used.
	 */

	@Override
	public void loadFile(Presentation presentation, String unusedFilename) {
		// Set the title of the presentation
		presentation.setTitle("Demo Presentation");

		// Adding the first slide
		Slide slide1 = new Slide();
		slide1.setTitle("JabberPoint");
		slide1.append(new TextItem(1, "The Java presentation tool"));
		slide1.append(new TextItem(2, "Copyright (c) 1996-2000: Ian Darwin"));
		slide1.append(new TextItem(2, "Copyright (c) 2000-now:"));
		slide1.append(new TextItem(2, "Gert Florijn and Sylvia Stuurman"));
		slide1.append(new TextItem(4, "Calling Jabberpoint without a filename"));
		slide1.append(new TextItem(4, "will show this presentation"));
		slide1.append(new TextItem(1, "Navigate:"));
		slide1.append(new TextItem(3, "Next slide: PgDn or Enter"));
		slide1.append(new TextItem(3, "Previous slide: PgUp or up-arrow"));
		slide1.append(new TextItem(3, "Quit: q or Q"));
		presentation.append(slide1);

		// Adding the second slide
		Slide slide2 = new Slide();
		slide2.setTitle("Demonstration of levels and styles");
		slide2.append(new TextItem(1, "Level 1"));
		slide2.append(new TextItem(2, "Level 2"));
		slide2.append(new TextItem(1, "Again level 1"));
		slide2.append(new TextItem(1, "Level 1 has style number 1"));
		slide2.append(new TextItem(2, "Level 2 has style number 2"));
		slide2.append(new TextItem(3, "This is how level 3 looks like"));
		slide2.append(new TextItem(4, "And this is level 4"));
		presentation.append(slide2);

		// Adding the third slide
		Slide slide3 = new Slide();
		slide3.setTitle("The third slide");
		slide3.append(new TextItem(1, "To open a new presentation,"));
		slide3.append(new TextItem(2, "use File->Open from the menu."));
		slide3.append(new TextItem(1, " "));
		slide3.append(new TextItem(1, "This is the end of the presentation."));

		// Assuming BitmapItem constructor takes (level, imagePath)
		slide3.append(new BitmapItem(1, "JabberPoint.jpg"));
		presentation.append(slide3);
	}

	/**
	 * Throws UnsupportedOperationException if an attempt is made to save the demo presentation.
	 *
	 * @param presentation The presentation object that would hypothetically be saved.
	 * @param unusedFilename The filename under which the presentation would hypothetically be saved.
	 */
	@Override
	public void saveFile(Presentation presentation, String unusedFilename) {
		throw new UnsupportedOperationException("Saving a demo presentation is not supported.");
	}
}
