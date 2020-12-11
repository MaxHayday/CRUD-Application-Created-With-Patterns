package com.maxhayday.crudapp.controller;

import com.maxhayday.crudapp.model.User;

import com.maxhayday.crudapp.repository.UserRepository;
import com.maxhayday.crudapp.repository.io.JavaIOUserRepositoryImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class UserController {
    private UserRepository repository;

    public UserController() throws IOException, ParseException {
        repository = new JavaIOUserRepositoryImpl();
    }

    public void save(User user) throws IOException {
        repository.save(user);
    }

    public List<User> getAll() throws IOException, ParseException {
        return repository.getAll();
    }

    public void update(User user) throws IOException {
        repository.update(user);
    }

    public void deleteById(Long id) throws IOException {
        repository.deleteById(id);
    }

    public User getById(Long id) throws IOException, ParseException {
        return repository.getById(id);
    }

}
