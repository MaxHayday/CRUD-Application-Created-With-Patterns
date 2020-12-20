package com.maxhayday.crudapp.view.observer;

import java.io.IOException;
import java.text.ParseException;

public interface Observed {
    void addObserver(ViewObserver obsorver);

    void removeObserver(ViewObserver obsorver);

    void notifyObserversAboutCreating() throws ParseException, IOException;

    void notifyPostObserverAboutCreating() throws ParseException, IOException;

    void notifyRegionObserverAboutCreating() throws ParseException, IOException;

    void notifyRegionObserverAboutShowingAll() throws IOException, ParseException;

    void notifyPostObserverAboutShowingAll() throws IOException, ParseException;

    void notifyObserversAboutUpdating(Long id) throws IOException;

    void notifyObserversAboutDeleting(Long id) throws ParseException, IOException;

    void notifyUserObserverAboutShowingAll() throws IOException, ParseException;

    void notifyUserObserverAboutGettingPostsByUserId(Long id) throws IOException, ParseException;

    void notifyPostObserverAboutDeletingPostById(Long id) throws IOException, ParseException;

    void notifyRegionObserverAboutDeletingRegionById(Long id) throws IOException, ParseException;
}
