package com.maxhayday.crudapp.repository;

import com.maxhayday.crudapp.model.Post;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    Post getById(Long id) throws IOException, ParseException;

    Post save(Post post) throws IOException;

    List<Post> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;
}
