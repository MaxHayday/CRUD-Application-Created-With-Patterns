package com.maxhayday.crudapp.view.observer;

public interface Observed {
    void addObserver(ViewObserver obsorver);

    void removeObserver(ViewObserver obsorver);

    void notifyObserversAboutCreating();

    void notifyObserversAboutUpdating(Long id);

    void notifyObserversAboutDeleting(Long id);

    void notifyObserversAboutGettingById(Long id);

    void notifyObserversAboutShowingAll();
}
