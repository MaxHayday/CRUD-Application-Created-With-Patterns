package com.maxhayday.crudapp.repository;

import com.maxhayday.crudapp.model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface UserRepository {
    User getById(Long id) throws IOException, ParseException;

    User save(User user) throws IOException;

    User update(User user) throws IOException;

    List<User> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;
}
