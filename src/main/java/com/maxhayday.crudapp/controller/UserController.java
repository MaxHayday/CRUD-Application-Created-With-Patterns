package com.maxhayday.crudapp.controller;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.PostBuilderImpl;
import com.maxhayday.crudapp.builder.RegionBuilderImpl;
import com.maxhayday.crudapp.builder.UserBuilderImpl;
import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.model.Region;
import com.maxhayday.crudapp.model.User;

import com.maxhayday.crudapp.repository.PostRepository;
import com.maxhayday.crudapp.repository.RegionRepository;
import com.maxhayday.crudapp.repository.UserRepository;
import com.maxhayday.crudapp.repository.io.JavaIOPostRepositoryImpl;
import com.maxhayday.crudapp.repository.io.JavaIORegionRepositoryImpl;
import com.maxhayday.crudapp.repository.io.JavaIOUserRepositoryImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {
    private UserRepository repository;
    private PostRepository postRepository;
    private RegionRepository regionRepository;
    private ModelDirector director;
    private User user;
    private Post post;
    private Region region;
    private List<User> userList;
    private List<Post> postList;


    public UserController() {
        try {
            repository = new JavaIOUserRepositoryImpl();
            postRepository = new JavaIOPostRepositoryImpl();
            regionRepository = new JavaIORegionRepositoryImpl();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        director = new ModelDirector();
        director.setUserBuilder(new UserBuilderImpl());
        director.setPostBuilder(new PostBuilderImpl());
        director.setRegionBuilder(new RegionBuilderImpl());
    }

    public void save(Long id, String firstName, String lastName, List<String> posts, String region, String role) {
        postList = new ArrayList<>();
        for (String p :
                posts) {
            postList.add(post = director.buildPost(null, p, null, null));
        }
        this.region = director.buildRegion(null, region);
        user = director.buildUser(id, firstName, lastName, postList, this.region, role);
        try {
            repository.save(user);
        } catch (IOException | ParseException e) {
            System.out.println("Something went wrong...");
        }
    }

    public List<User> getAll() {
        userList = new ArrayList<>();
        try {
            userList = repository.getAll();
            return userList;
        } catch (IOException | ParseException e) {
            System.out.println("You haven`t users.");
        }
        return null;
    }

    public void update(Long id, String firstName, String lastName, List<String> posts, String region, String role) {
        user = null;
        try {
            user = repository.getById(id);
            this.region = user.getRegion();
            postList = user.getPosts();
            user = director.buildUser(id, firstName, lastName, postList.stream().collect(Collectors.toList()), this.region, role);
            repository.update(user);
        } catch (IOException | ParseException e) {
            System.out.println("Something went wrong...");
        }
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (IOException e) {
            System.out.println("Wrong id.");
        }
    }

    public User getById(Long id) {
        try {
            return repository.getById(id);
        } catch (IOException | ParseException e) {
            System.out.println("Wrong id.");
        }
        return null;
    }
}
