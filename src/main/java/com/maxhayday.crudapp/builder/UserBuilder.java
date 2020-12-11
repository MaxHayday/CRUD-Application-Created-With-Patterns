package com.maxhayday.crudapp.builder;


import com.maxhayday.crudapp.model.User;

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
