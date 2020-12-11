package com.maxhayday.crudapp.view.observer;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.UserBuilderImpl;
import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.model.Region;
import com.maxhayday.crudapp.model.User;
import com.maxhayday.crudapp.controller.PostController;
import com.maxhayday.crudapp.controller.RegionController;
import com.maxhayday.crudapp.controller.UserController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UserViewObserver implements ViewObserver {
    private UserController userController;
    private PostController postController;
    private RegionController regionController;
    private ModelDirector director;
    private List<User> userList;
    private List<Post> postList;
    private List<Region> regionList;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Long id = 0L;
    private String firstNameData = "", lastNameData = "", data;
    private User user;

    public UserViewObserver() throws IOException, ParseException {
        userController = new UserController();
        postController = new PostController();
        regionController = new RegionController();
        director = new ModelDirector();
        director.setUserBuilder(new UserBuilderImpl());
    }

    @Override
    public void create() {
        postList = new ArrayList<>();
        try {
            System.out.println("Write first name of user: ");
            firstNameData = reader.readLine();
            if (checkForCorrectInputWord(firstNameData)) {
                System.out.println("Write last name of user: ");
                lastNameData = reader.readLine();
                if (checkForCorrectInputWord(lastNameData)) {
                    System.out.println("Choose role of user: ");
                    System.out.println("1: User");
                    System.out.println("2: Admin");
                    System.out.println("3: Moderator");
                    data = reader.readLine();
                    user = director.buildUser(id, firstNameData, lastNameData, data);
                    userController.save(user);
                } else System.out.println("You wrote wrong number");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong. Please try again.");
        }

    }

    @Override
    public void update(Long id) {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
            }
            if (userList.size() < id || id <= 0) {
                System.out.println("You have not user with ID: " + id);
            }
            System.out.println("Write first name of user: ");
            firstNameData = reader.readLine();
            if (checkForCorrectInputWord(firstNameData)) {
                System.out.println("Write last name of user: ");
                lastNameData = reader.readLine();
                if (checkForCorrectInputWord(lastNameData)) {
                    System.out.println("Choose role of user: ");
                    System.out.println("1: User");
                    System.out.println("2: Admin");
                    System.out.println("3: Moderator");
                    data = reader.readLine();
                    user = director.buildUser(id, firstNameData, lastNameData, data);
                    userController.update(user);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Something went wrong. Please try again.");
        }
    }

    @Override
    public void getById(Long id) {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return;
            }
            user = userController.getById(id);
            if (user == null) {
                System.out.println("YOU HAVE NOT USER WITH ID: " + id);
                return;
            }
            System.out.println("================================================================================================================================================");
            System.out.printf("%-5s%-15s%-20s%-20s%-20s%-20s%-25s%-20s%n", "ID", "FIRST_NAME", "LAST_NAME", "ROLE", "REGION", "POSTS", "POST CREATED", "POST UPDATED");
            System.out.println("================================================================================================================================================");
            System.out.printf("%-5s%-15s%-20s%-20s", user.getId(), user.getName(), user.getLastName(), user.getRole());
        } catch (IOException | ParseException e) {
            System.out.println("You have not users.");
        }
    }

    @Override
    public void getAll() {
        try {
            userList = userController.getAll();
            postList = postController.getAll();
            regionList = regionController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return;
            }
            System.out.println("======================================================================================================================");
            System.out.printf("%-5s%-15s%-25s%-25s%-25s%-25s%n", "ID", "FIRST_NAME", "LAST_NAME", "ROLE", "REGION", "POSTS");
            System.out.println("======================================================================================================================");
            int k = 0, i = 0, j = 0;
            for (; i < userList.size(); i++) {
                System.out.printf("%-5s%-15s%-25s%-25s", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getLastName(), userList.get(i).getRole());
                for (; j < regionList.size(); ) {
                    System.out.printf("%-25s", regionList.get(j).getName());
                    for (; k < postList.size(); ) {
                        System.out.printf("%-25s%n", postList.get(k).getName());
                        break;
                    }
                    k++;
                    break;
                }
                j++;
            }
        } catch (IOException | ParseException e) {
            System.out.println("You have not users.");
        }
    }


    @Override
    public void delete(Long id) {
        try {
            userList = userController.getAll();
            if (userList.isEmpty()) {
                System.out.println("You have not users.");
                return;
            }
            if (userList.size() < id || id <= 0) {
                System.out.println("You have not user with ID: " + id);
                return;
            }
            userController.deleteById(id);
        } catch (IOException | ParseException e) {
            System.out.println("Write correct id.");
        }
    }

    private boolean checkForCorrectInputWord(String s) {
        if (!s.isEmpty() && !s.matches("[0-9]")) {
            return true;
        } else {
            System.out.println("You wrote incorrect word.");
            return false;
        }
    }
}
