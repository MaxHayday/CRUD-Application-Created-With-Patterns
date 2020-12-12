package com.maxhayday.crudapp.view.observer;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.PostBuilderImpl;
import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.controller.PostController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PostViewObserver implements ViewObserver {
    private PostController controller;
    private ModelDirector director;
    private List<Post> postList;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String data;
    private Post post;

    public PostViewObserver() throws IOException, ParseException {
        controller = new PostController();
        director = new ModelDirector();
        director.setPostBuilder(new PostBuilderImpl());
    }

    @Override
    public void create() {
        System.out.println("Write name of post: ");
        try {
            data = reader.readLine();
            if (!(data.isEmpty() || data.matches("[0-9]") || data.matches("[^\\w]"))) {
                director.setPostBuilder(new PostBuilderImpl());
                post = director.buildPost(0l, data, null, null);
                controller.save(post);
            } else return;
        } catch (IOException e) {
            System.out.println("Please wright correct name;");
        }
    }

    @Override
    public void update(Long id) {
        try {
            postList = controller.getAll();
            if (postList.isEmpty()) {
                return;
            }
            System.out.println("Write new name of post: ");
            data = reader.readLine();
            postList = new ArrayList<>();
            post = director.buildPost(id, data, null, null);
            postList.add(post);
            controller.update(postList);
        } catch (IOException | ParseException | NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    @Override
    public void getById(Long id) {
        try {
            postList = controller.getAll();
            if (postList.isEmpty()) {
                return;
            }
            Post post = controller.getById(id);
            System.out.printf("%-20s%-25s%-20s%n", post.getContent(), post.getCreated(), post.getUpdated());
        } catch (IOException | NumberFormatException | ParseException e) {
            System.out.println("Write correct id.");
        }
    }

    @Override
    public void getAll() {
        try {
            postList = controller.getAll();
            if (postList.isEmpty()) {
                return;
            }
//            for (Post i :
//                    postList) {
//                System.out.printf("%-25s%n", i.getName());
//            }
        } catch (IOException | ParseException | NumberFormatException e) {
            System.out.println("You have not posts.");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            postList = controller.getAll();
            if (postList.isEmpty()) {
                return;
            }
            controller.deleteById(id);
        } catch (IOException | ParseException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }
}
