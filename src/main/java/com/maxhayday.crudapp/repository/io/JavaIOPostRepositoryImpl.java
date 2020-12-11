package com.maxhayday.crudapp.repository.io;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.PostBuilderImpl;
import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.repository.PostRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class JavaIOPostRepositoryImpl implements PostRepository {
    private static final Path postPath = Paths.get("//home/max/IdeaProjects/CRUDConsoleApplicationCreatedWithPatterns/src/resources/post.txt");
    private static Long countId = 0L;
    private List<String> list;
    private List<Post> postList;
    private BufferedReader reader;
    private BufferedWriter writer;
    private BasicFileAttributes attr;
    private ModelDirector director;
    private Post post;

    public JavaIOPostRepositoryImpl() throws IOException {
        director = new ModelDirector();
        director.setPostBuilder(new PostBuilderImpl());
        attr = Files.readAttributes(postPath, BasicFileAttributes.class);
        postList = getAll();
        for (Post p :
                postList) {
            if (p.getId() > countId) {
                countId = p.getId();
            } else countId = 0L;
        }
    }


    @Override
    public Post getById(Long id) throws IOException {
        reader = Files.newBufferedReader(postPath);
        list = new ArrayList<>();
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        for (String s :
                list) {
            if (!(s.isEmpty())) {
                if (Long.parseLong(s.split("\\.")[0]) == id) {
                    post = director.buildPost(id, s.split("\\.+|[\\s]")[1], attr.creationTime().toString().split("\\.")[0], attr.lastModifiedTime().toString().split("\\.")[0]);
                    return post;
                }
            } else break;
        }
        return null;
    }

    @Override
    public Post save(Post post) throws IOException {
        String postStr = (++countId) + "." + post.getName() + " " + attr.creationTime().toString().split("\\.")[0] + " " + attr.lastModifiedTime().toString().split("\\.")[0] + "\n";
        Files.write(postPath, postStr.getBytes(), StandardOpenOption.APPEND);
        return post;
    }

    @Override
    public Post update(List<Post> posts) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(postPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(postPath);
        for (String s : list) {
            for (Post p :
                    posts) {
                if (Long.parseLong(s.split("\\.")[0]) == p.getId() && (!s.isEmpty())) {
                    writer.write(p.getId() + "." + p.getName() + " " + attr.creationTime().toString().split("\\.")[0] + " " + attr.lastModifiedTime().toString().split("\\.")[0]);
                } else {
                    writer.write(s);
                }
                writer.newLine();
            }
        }
        writer.close();
        return null;
    }

    @Override
    public List<Post> getAll() throws IOException {
        postList = new ArrayList<>();
        reader = Files.newBufferedReader(postPath);
        while (reader.ready()) {
            String[] line = reader.readLine().split("\\s+|\\.\\s*");
            if (line.length != 0 && !(line[0].isEmpty())) {
                post = director.buildPost(Long.parseLong(line[0]), line[1], attr.creationTime().toString().split("\\.")[0], attr.lastModifiedTime().toString().split("\\.")[0]);
                postList.add(post);
            }
        }
        reader.close();
        return postList;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(postPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(postPath);
        for (String s :
                list) {
            if (Long.parseLong(s.split("\\.")[0]) != id) {
                writer.write(s);
                writer.newLine();
            }
        }
        writer.close();
        --countId;
    }
}
