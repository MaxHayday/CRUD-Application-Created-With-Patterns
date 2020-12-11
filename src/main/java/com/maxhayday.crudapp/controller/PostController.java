package com.maxhayday.crudapp.controller;

import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.repository.PostRepository;
import com.maxhayday.crudapp.repository.io.JavaIOPostRepositoryImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class PostController {
    private PostRepository repository;

    public PostController() throws IOException {
        repository = new JavaIOPostRepositoryImpl();
    }

    public void save(Post post) throws IOException {
        repository.save(post);
    }

    public List<Post> getAll() throws IOException, ParseException {
        return repository.getAll();
    }

    public void update(List<Post> posts) throws IOException {
        repository.update(posts);
    }

    public void deleteById(Long id) throws IOException {
        repository.deleteById(id);
    }

    public Post getById(Long id) throws IOException, ParseException {
        return repository.getById(id);
    }
}
