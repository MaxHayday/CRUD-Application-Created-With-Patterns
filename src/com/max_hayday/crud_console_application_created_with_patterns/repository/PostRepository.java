package com.max_hayday.crud_console_application_created_with_patterns.repository;

import com.max_hayday.crud_console_application_created_with_patterns.builder.model.Post;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface PostRepository {
    Post getById(Long id) throws IOException, ParseException;

    Post save(Post post) throws IOException;

    Post update(List<Post> posts) throws IOException;

    List<Post> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;
}
