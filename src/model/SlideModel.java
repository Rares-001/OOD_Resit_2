package model;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class SlideModel implements Observable {
    private String title;
    private List<SlideItemModel> items;
    private List<Observer> observers = new ArrayList<>();

    // Constructor
    public SlideModel() {
        items = new ArrayList<>();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        // Notify observers about the title change
        notifyObservers();
    }

    public List<SlideItemModel> getItems() {
        return items;
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

    // Methods to manipulate slide items
    public void addItem(SlideItemModel item) {
        items.add(item);
        // Notify observers about the item addition
        notifyObservers();
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            // Notify observers about the item removal
            notifyObservers();
        }
    }

    public SlideItemModel getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }
}
