package com.maxhayday.crudapp.model;

import java.util.List;

public class User extends AbstractBaseEntity {
    private String name;
    private String lastName;
    private Role role;
    private List<Post> posts;
    private Region region;


    public Role getRole() {
        return role;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
