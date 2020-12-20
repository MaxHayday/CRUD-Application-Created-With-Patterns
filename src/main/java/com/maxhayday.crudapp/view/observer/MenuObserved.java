package com.maxhayday.crudapp.view.observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MenuObserved implements Observed {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String data;
    private Long id = 0L;

    private List<ViewObserver> subscribers = new ArrayList<>();


    public void showMenu() throws IOException, ParseException {
        do {
            System.out.println("\n----------------------------------------- Choose one of options ----------------------------------------");
            System.out.println("1: Create user;");
            System.out.println("2: Show users;");
            System.out.println("3: Show posts;");
            System.out.println("4: Show regions;");
            System.out.println("5: Update user by Id;");
            System.out.println("6: Delete user by Id;");
            System.out.println("7: Get posts by user id;");
            System.out.println("8: Create post;");
            System.out.println("9: Create region;");
            System.out.println("10: Delete post by id;");
            System.out.println("11: Delete region by id;");
            System.out.println("For exit write exit;");
            data = reader.readLine();
            switch (data) {
                case "1":
                    notifyObserversAboutCreating();
                    break;
                case "2":
                    notifyUserObserverAboutShowingAll();
                    break;
                case "3":
                    notifyPostObserverAboutShowingAll();
                    break;
                case "4":
                    notifyRegionObserverAboutShowingAll();
                    break;
                case "5":
                    System.out.println("Write id of user which do you want to update: ");
                    try {
                        data = reader.readLine();
                        id = Long.parseLong(data);
                        notifyObserversAboutUpdating(id);
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                        break;
                    }
                    break;
                case "6":
                    System.out.println("Write id of user which do you want to delete: ");
                    try {
                        id = Long.parseLong(reader.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                        break;
                    }
                    notifyObserversAboutDeleting(id);
                    break;
                case "7":
                    System.out.println("Write id of user: ");
                    try {
                        id = Long.parseLong(reader.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                        break;
                    }
                    notifyUserObserverAboutGettingPostsByUserId(id);
                    break;
                case "8":
                    notifyPostObserverAboutCreating();
                    break;
                case "9":
                    notifyRegionObserverAboutCreating();
                    break;
                case "10":
                    System.out.println("Write id of post: ");
                    id = Long.parseLong(reader.readLine());
                    notifyPostObserverAboutDeletingPostById(id);
                    break;
                case "11":
                    System.out.println("Write id of region: ");
                    id = Long.parseLong(reader.readLine());
                    notifyRegionObserverAboutDeletingRegionById(id);
                    break;

            }
        } while (!data.equals("exit"));
    }

    @Override
    public void addObserver(ViewObserver obsorver) {
        this.subscribers.add(obsorver);
    }

    @Override
    public void removeObserver(ViewObserver obsorver) {
        this.subscribers.remove(obsorver);
    }

    @Override
    public void notifyObserversAboutCreating() throws ParseException, IOException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(UserViewObserver.class)) {
                o.create();
            }
        }
    }

    @Override
    public void notifyPostObserverAboutCreating() throws ParseException, IOException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(PostViewObserver.class)) {
                o.create();
            }
        }
    }

    @Override
    public void notifyRegionObserverAboutCreating() throws ParseException, IOException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(RegionViewObserver.class)) {
                o.create();
            }
        }
    }

    @Override
    public void notifyRegionObserverAboutShowingAll() throws IOException, ParseException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(RegionViewObserver.class)) {
                o.getAll();
            }
        }
    }

    @Override
    public void notifyPostObserverAboutShowingAll() throws IOException, ParseException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(PostViewObserver.class)) {
                o.getAll();
            }
        }
    }

    @Override
    public void notifyObserversAboutUpdating(Long id) throws IOException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(UserViewObserver.class)) {
                o.update(id);
            }
        }
    }

    @Override
    public void notifyObserversAboutDeleting(Long id) throws ParseException, IOException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(UserViewObserver.class)) {
                o.delete(id);
            }
        }
    }

    @Override
    public void notifyUserObserverAboutShowingAll() throws IOException, ParseException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(UserViewObserver.class)) {
                o.getAll();
            }
        }
    }

    @Override
    public void notifyUserObserverAboutGettingPostsByUserId(Long id) {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(UserViewObserver.class)) {
                o.getById(id);
            }
        }
    }

    @Override
    public void notifyPostObserverAboutDeletingPostById(Long id) throws ParseException, IOException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(PostViewObserver.class)) {
                o.delete(id);
            }
        }
    }

    @Override
    public void notifyRegionObserverAboutDeletingRegionById(Long id) throws ParseException, IOException {
        for (ViewObserver o :
                subscribers) {
            if (o.getClass().equals(RegionViewObserver.class)) {
                o.delete(id);
            }
        }
    }

}
