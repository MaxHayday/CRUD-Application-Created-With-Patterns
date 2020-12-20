package com.maxhayday.crudapp.repository;

import com.maxhayday.crudapp.model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface UserRepository extends GenericRepository<User, Long> {
    User getById(Long id) throws IOException, ParseException;

    User save(User user) throws IOException, ParseException;

    User update(User user) throws IOException, ParseException;

    List<User> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;

    void deletePostById(Long id) throws IOException, ParseException;

    void deleteRegionById(Long id) throws IOException, ParseException;
}
