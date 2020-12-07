package com.max_hayday.crud_console_application_created_with_patterns.builder;

import com.max_hayday.crud_console_application_created_with_patterns.builder.model.Post;

public abstract class PostBuilder {
    Post post;

    void createPost() {
        post = new Post();
    }

    abstract void buildID(Long id);

    abstract void buildContent(String content);

    abstract void buildCreated(String created);

    abstract void buildUpdated(String updated);

    Post getPost() {
        return post;
    }

}
