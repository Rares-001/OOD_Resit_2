package observer;

/**
 * Interface for an observer in the observer pattern. Classes that implement
 * Observer update their state in response to notifications from Observables.
 */

public interface Observer {

    /**
     * Called by the Observable object whenever there's a change. Implementations
     * should update their state to reflect the change.
     */

    void update();
}
