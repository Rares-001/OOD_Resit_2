package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface defining the methods required for an observable object in the observer pattern.
 */

public interface Observable {
    List<Observer> observers = new ArrayList<>();

    /**
     * Registers an observer to be notified of changes.
     *
     * @param observer The observer to register.
     */

    default void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes a registered observer.
     *
     * @param observer The observer to remove.
     */

    default void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers of a change.
     */

    default void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
