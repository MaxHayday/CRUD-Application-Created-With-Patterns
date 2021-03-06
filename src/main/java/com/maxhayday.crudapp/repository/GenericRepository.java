package com.maxhayday.crudapp.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface GenericRepository<T,ID> {
    T getById(Long id) throws IOException, ParseException;

    T save(T t) throws IOException, ParseException;

    T update(T t) throws IOException, ParseException;

    List<T> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;
}
