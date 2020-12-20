package com.maxhayday.crudapp.view.observer;

import java.io.IOException;
import java.text.ParseException;

public interface ViewObserver {
    void create() throws ParseException, IOException;

    void update(Long id) throws IOException;

    void getById(Long id);

    void getAll() throws IOException, ParseException;

    void delete(Long id) throws ParseException, IOException;
}
