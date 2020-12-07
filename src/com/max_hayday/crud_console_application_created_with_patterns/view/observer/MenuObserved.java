package com.max_hayday.crud_console_application_created_with_patterns.view.observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MenuObserved implements Observed {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String data;
    private Long id = 0L;

    private List<ViewObserver> subscribers = new ArrayList<>();


    public void showMenu() throws IOException {
        do {
            System.out.println("\n----------------------------------------------- Choose one of options -----------------------------------------------");
            System.out.println("1: Create user;");
            System.out.println("2: Show users;");
            System.out.println("3: Update user;");
            System.out.println("4: Delete user by Id;");
            System.out.println("5: Get user by Id;");
            System.out.println("For exit write exit");
            data = reader.readLine();
            switch (data) {
                case "1":
                    notifyObserversAboutCreating();
                    break;
                case "2":
                    notifyObserversAboutShowingAll();
                    break;
                case "3":
                    System.out.println("Write id of user which do you want to change: ");
                    try {
                        data = reader.readLine();
                        id = Long.parseLong(data);
                        notifyObserversAboutUpdating(id);
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                        break;
                    }
                    break;
                case "4":
                    System.out.println("Write id of user which do you want to delete: ");
                    try {
                        id = Long.parseLong(reader.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                        break;
                    }
                    notifyObserversAboutDeleting(id);
                    break;
                case "5":
                    System.out.println("Write id of user: ");
                    try {
                        id = Long.parseLong(reader.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("You need to write number.");
                        break;
                    }
                    notifyObserversAboutGettingById(id);
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
    public void notifyObserversAboutCreating() {
        for (ViewObserver o :
                subscribers) {
            o.create();
        }
    }

    @Override
    public void notifyObserversAboutUpdating(Long id) {
        for (ViewObserver o :
                subscribers) {
            o.update(id);
        }
    }

    @Override
    public void notifyObserversAboutDeleting(Long id) {
        for (ViewObserver o :
                subscribers) {
            o.delete(id);
        }
    }

    @Override
    public void notifyObserversAboutGettingById(Long id) {
        for (ViewObserver o :
                subscribers) {
            o.getById(id);
        }
    }

    @Override
    public void notifyObserversAboutShowingAll() {
        for (ViewObserver o :
                subscribers) {
            o.getAll();
        }
    }

}
