package com.max_hayday.crud_console_application_created_with_patterns.builder;


import com.max_hayday.crud_console_application_created_with_patterns.builder.model.User;

public abstract class UserBuilder {
    User user;


    void createUser() {
        user = new User();
    }

    abstract void buildID(Long id);

    abstract void buildFirstName(String name);

    abstract void buildLastName(String lastName);

    abstract void buildRole(String role);

    User getUser() {
        return user;
    }

}
