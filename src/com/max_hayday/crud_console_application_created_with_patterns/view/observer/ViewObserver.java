package com.max_hayday.crud_console_application_created_with_patterns.view.observer;

public interface ViewObserver {
    void create();

    void update(Long id);

    void getById(Long id);

    void getAll();

    void delete(Long id);
}
