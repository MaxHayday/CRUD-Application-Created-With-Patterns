package com.maxhayday.crudapp.builder;


import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.model.Region;
import com.maxhayday.crudapp.model.User;

import java.util.List;

public abstract class UserBuilder {
    User user;

    void createUser() {
        user = new User();
    }

    abstract void buildID(Long id);

    abstract void buildFirstName(String name);

    abstract void buildLastName(String lastName);

    abstract void buildListPosts(List<Post> posts);

    abstract void buildRegion(Region region);

    abstract void buildRole(String role);

    User getUser() {
        return user;
    }

}
