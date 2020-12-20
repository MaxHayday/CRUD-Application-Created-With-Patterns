package com.maxhayday.crudapp.view.observer;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.PostBuilderImpl;
import com.maxhayday.crudapp.controller.UserController;
import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.controller.PostController;
import com.maxhayday.crudapp.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class PostViewObserver implements ViewObserver {
    private PostController postController;
    private UserController userController;
    private ModelDirector director;
    private List<Post> postList;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String data = "", postStr = "";
    private Post post;
    private User user;

    public PostViewObserver() {
        postController = new PostController();
        userController = new UserController();
        director = new ModelDirector();
        director.setPostBuilder(new PostBuilderImpl());
    }

    @Override
    public void create() throws IOException {
        do {
            System.out.println("Write post of user");
            System.out.println("If you finish creates posts write exit");
            postStr = reader.readLine();
            if (postStr.equals("exit")) break;
            postController.save(null, postStr, null, null);
        } while (!data.equals("exit"));
    }

    @Override
    public void update(Long id) throws IOException {
        user = userController.getById(id);
        if (user == null) {
            return;
        }
        postList = user.getPosts();
        if (postList.isEmpty()) {
            System.out.println("You haven`t posts.");
            return;
        }
        System.out.println("======================================================================================================================");
        System.out.printf("%-5s%-15s%-25s%-25s%n", "ID", "Content", "Created", "Updated");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        for (Post p : postList) {
            System.out.printf("%-5s%-15s%-25s%-25s%n", p.getId(), p.getContent(), p.getCreated(), p.getUpdated());
        }
        System.out.println("Write id of post which do you want to change: ");
        data = reader.readLine();
        post = postController.getById(Long.valueOf(data));
        System.out.println("Write new name of post: ");
        data = reader.readLine();
        postController.update(post.getId(), data, null, null);

    }

    @Override
    public void getById(Long id) {
        postList = postController.getAll();
        if (postList.isEmpty()) {
            System.out.println("You haven`t post with " + id + " id.");
            return;
        }
    }

    @Override
    public void getAll() {
        postList = postController.getAll();
        if (postList.isEmpty()) {
            System.out.println("You haven`t posts.");
            return;
        }
        System.out.println("========================================================================================================");
        System.out.printf("%-5s%-55s%-25s%-25s%n", "ID", "POST", "CREATED", "UPDATED");
        System.out.println("========================================================================================================");
        for (Post i :
                postList) {
            System.out.printf("%-5s%-55s%-25s%-25s%n", i.getId(), i.getContent(), i.getCreated(), i.getUpdated());
        }
    }

    @Override
    public void delete(Long id) {
        post = postController.getById(id);
        if (post == null) {
            System.out.println("You haven`t post with " + id + " id.");
            return;
        }
        postController.deleteById(id);
    }
}
