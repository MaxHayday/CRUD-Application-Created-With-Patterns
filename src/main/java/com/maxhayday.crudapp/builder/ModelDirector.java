package com.maxhayday.crudapp.builder;


import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.model.Region;
import com.maxhayday.crudapp.model.User;

import java.util.List;

public class ModelDirector {
    private UserBuilder builder;
    private RegionBuilder regionBuilder;
    private PostBuilder postBuilder;

    public void setRegionBuilder(RegionBuilder regionBuilder) {
        this.regionBuilder = regionBuilder;
    }

    public void setPostBuilder(PostBuilder postBuilder) {
        this.postBuilder = postBuilder;
    }

    public void setUserBuilder(UserBuilder builder) {
        this.builder = builder;
    }

    public Post buildPost(Long id, String content, String created, String updated) {
        postBuilder.createPost();
        postBuilder.buildID(id);
        postBuilder.buildContent(content);
        postBuilder.buildCreated(created);
        postBuilder.buildUpdated(updated);
        Post post = postBuilder.getPost();
        return post;
    }

    public User buildUser(Long id, String firstName, String lastName, List<Post> posts, Region region, String role) {
        builder.createUser();
        builder.buildID(id);
        builder.buildFirstName(firstName);
        builder.buildLastName(lastName);
        builder.buildListPosts(posts);
        builder.buildRegion(region);
        builder.buildRole(role);
        User user = builder.getUser();
        return user;
    }

    public Region buildRegion(Long id, String name) {
        regionBuilder.createRegion();
        regionBuilder.buildID(id);
        regionBuilder.buildName(name);
        Region region = regionBuilder.getRegion();
        return region;
    }
}
