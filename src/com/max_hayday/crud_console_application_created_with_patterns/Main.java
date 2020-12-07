package com.max_hayday.crud_console_application_created_with_patterns;

import com.max_hayday.crud_console_application_created_with_patterns.view.observer.MenuObserved;
import com.max_hayday.crud_console_application_created_with_patterns.view.observer.PostViewObserver;
import com.max_hayday.crud_console_application_created_with_patterns.view.observer.RegionViewObserver;
import com.max_hayday.crud_console_application_created_with_patterns.view.observer.UserViewObserver;

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
