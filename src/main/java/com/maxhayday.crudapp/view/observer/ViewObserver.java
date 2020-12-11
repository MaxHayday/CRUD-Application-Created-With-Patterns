package com.maxhayday.crudapp.view.observer;

public interface ViewObserver {
    void create();

    void update(Long id);

    void getById(Long id);

    void getAll();

    void delete(Long id);
}
