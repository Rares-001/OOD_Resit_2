package observer;

import java.util.ArrayList;
import java.util.List;

public interface Observable {
    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     *
     * @param o an observer to be added.
     */
    void addObserver(Observer o);

    /**
     * Deletes an observer from the set of observers of this object.
     *
     * @param o the observer to be deleted.
     */
    void removeObserver(Observer o);

    /**
     * If this object has changed, as indicated by the hasChanged method,
     * then notify all of its observers and then call the clearChanged
     * method to indicate that this object has no longer changed.
     */
    void notifyObservers();
}
abstract class AbstractObservable implements Observable {
    private List<Observer> observers = new ArrayList<>();

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
        for (Observer observer : observers) {
            observer.update(this, null);
        }
    }
}
