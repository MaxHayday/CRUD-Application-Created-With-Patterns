package com.maxhayday.crudapp;

import com.maxhayday.crudapp.view.observer.MenuObserved;
import com.maxhayday.crudapp.view.observer.PostViewObserver;
import com.maxhayday.crudapp.view.observer.RegionViewObserver;
import com.maxhayday.crudapp.view.observer.UserViewObserver;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        MenuObserved menu = new MenuObserved();
        menu.addObserver(new UserViewObserver());
        menu.addObserver(new RegionViewObserver());
        menu.addObserver(new PostViewObserver());
        menu.showMenu();
    }
}
