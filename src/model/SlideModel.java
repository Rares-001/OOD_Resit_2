package model;

import observer.Observable;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class SlideModel implements Observable
{
    private String title;
    private List<SlideItemModel> items;
    private List<Observer> observers;
    private int currentSlideIndex = 0;

    public SlideModel()
    {
        items = new ArrayList<>();
        observers = new ArrayList<>();
    }

    // Getters and Setters
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
        notifyObservers();
    }

    public List<SlideItemModel> getItems()
    {
        return items;
    }

    public void addItem(SlideItemModel item)
    {
        items.add(item);
        notifyObservers();
    }

    public void removeItem(int index)
    {
        if (index >= 0 && index < items.size())
        {
            items.remove(index);
            notifyObservers();
        }
    }

    public int getCurrentSlideIndex()
    {
        return currentSlideIndex;
    }

    public void setCurrentSlideIndex(int currentSlideIndex)
    {
        this.currentSlideIndex = currentSlideIndex;
    }

    public int getTotalSlides()
    {
        return items.size();
    }

    // Observer-related methods
    @Override
    public void addObserver(Observer observer)
    {
        if (observer != null && !observers.contains(observer))
        {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers()
    {
        for (Observer observer : observers)
        {
            observer.update(this, null);
        }
    }

}
