package com.maxhayday.crudapp.repository;

import com.maxhayday.crudapp.model.Post;

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
