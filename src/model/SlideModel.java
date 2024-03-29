package model;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class SlideModel implements Observable {
    private String title;
    private List<SlideItemModel> items;
    private List<Observer> observers;

    public SlideModel() {
        items = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyObservers();
    }

    public List<SlideItemModel> getItems() {
        return items;
    }

    public void addItem(SlideItemModel item) {
        items.add(item);
        notifyObservers();
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            notifyObservers();
        }
    }

    public SlideItemModel getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this, null);
        }
    }

}
