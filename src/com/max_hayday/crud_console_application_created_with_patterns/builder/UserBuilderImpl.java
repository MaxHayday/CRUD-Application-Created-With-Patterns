package com.max_hayday.crud_console_application_created_with_patterns.builder;

import com.max_hayday.crud_console_application_created_with_patterns.builder.model.Role;


public class UserBuilderImpl extends UserBuilder {

    @Override
    void buildID(Long id) {
        user.setId(id);
    }

    @Override
    void buildFirstName(String firstName) {
        user.setName(firstName);
    }

    @Override
    void buildLastName(String lastName) {
        user.setLastName(lastName);
    }

    @Override
    void buildRole(String role) {
        if (role.equals("USER") || role.equals("1")) {
            user.setRole(Role.USER);
        } else if (role.equals("ADMIN") || role.equals("2")) {
            user.setRole(Role.ADMIN);
        } else if (role.equals("MODERATOR") || role.equals("3")) {
            user.setRole(Role.MODERATOR);
        }
    }
}
