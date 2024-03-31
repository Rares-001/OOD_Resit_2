package model;

import java.io.IOException;

public interface DataAccessInterface
{
    /**
     * Loads a presentation from a specified path.
     * In the case of the DemoPresentation, the path parameter may be ignored as it uses built-in demo data.
     *
     * @param path the file path from which to load the presentation.
     * @return the loaded PresentationModel object.
     * @throws IOException if there is an issue loading the file.
     */
    PresentationModel loadPresentation(String path) throws IOException;

    /**
     * Saves a presentation to a specified path.
     * In the case of the DemoPresentation, this method may throw an exception as the demo cannot be saved.
     *
     * @param presentation the PresentationModel object to save.
     * @param path the file path to which to save the presentation.
     * @throws IOException if there is an issue saving the file.
     */
    void savePresentation(PresentationModel presentation, String path) throws IOException;
}
