package com.maxhayday.crudapp.builder;

import com.maxhayday.crudapp.model.Post;

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
