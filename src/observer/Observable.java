package observer;

import java.util.ArrayList;
import java.util.List;

public interface Observable
{
    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     *
     * @param o an observer to be added.
     */
    void addObserver(Observer o);

    /**
     * If this object has changed, as indicated by the hasChanged method,
     * then notify all of its observers and then call the clearChanged
     * method to indicate that this object has no longer changed.
     */
    void notifyObservers();
}

