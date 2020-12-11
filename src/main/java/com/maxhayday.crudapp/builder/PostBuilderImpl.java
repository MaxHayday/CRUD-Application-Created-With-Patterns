package com.maxhayday.crudapp.builder;

public class PostBuilderImpl extends PostBuilder {

    @Override
    void buildID(Long id) {
        post.setId(id);
    }

    @Override
    void buildContent(String content) {
        post.setName(content);
    }

    @Override
    void buildCreated(String created) {
        post.setCreated(created);
    }

    @Override
    void buildUpdated(String updated) {
        post.setUpdated(updated);
    }
}
