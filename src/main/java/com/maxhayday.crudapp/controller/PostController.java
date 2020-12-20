package com.maxhayday.crudapp.controller;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.PostBuilderImpl;
import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.repository.PostRepository;
import com.maxhayday.crudapp.repository.UserRepository;
import com.maxhayday.crudapp.repository.io.JavaIOPostRepositoryImpl;
import com.maxhayday.crudapp.repository.io.JavaIOUserRepositoryImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class PostController {
    private ModelDirector modelDirector;
    private PostRepository repository;
    private UserRepository userRepository;
    private Post post;

    public PostController() {
        modelDirector = new ModelDirector();
        try {
            repository = new JavaIOPostRepositoryImpl();
            userRepository = new JavaIOUserRepositoryImpl();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        modelDirector.setPostBuilder(new PostBuilderImpl());
    }

    public void save(Long id, String content, String created, String updated) {
        post = modelDirector.buildPost(id, content, created, updated);
        try {
            repository.save(post);
        } catch (IOException e) {
            System.out.println("You wrote wrong information.");
        }
    }

    public List<Post> getAll() {
        try {
            return repository.getAll();
        } catch (IOException | ParseException e) {
            System.out.println("You haven`t posts.");
        }
        return null;
    }

    public void update(Long id, String content, String created, String updated) {
        post = modelDirector.buildPost(id, content, created, updated);
        try {
            repository.update(post);
        } catch (ParseException | IOException e) {
            System.out.println("You wrote wrong information.");
        }
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
            userRepository.deletePostById(id);
        } catch (IOException | ParseException e) {
            System.out.println("Wrong id.");
        }
    }

    public Post getById(Long id) {
        try {
            return repository.getById(id);
        } catch (IOException | ParseException e) {
            System.out.println("Wrong id.");
        }
        return null;
    }
}
