package model;

import java.io.IOException;

/**
 * Abstract class for accessing presentation data
 * concrete implementations for loading and saving presentations.
 */

public abstract class Accessor {

	/**
	 * Loads a presentation from a specified source.
	 *
	 * @param presentation The presentation object to load data into.
	 * @param source The source from which to load the presentation
	 * @throws IOException If an error occurs during loading.
	 */
	public abstract void loadFile(Presentation presentation, String source) throws IOException;

	/**
	 * Saves the given presentation to a specified destination.
	 *
	 * @param presentation The presentation object to save.
	 * @param destination The destination to which the presentation should be saved
	 * @throws IOException If an error occurs during saving.
	 */
	public abstract void saveFile(Presentation presentation, String destination) throws IOException;
}
