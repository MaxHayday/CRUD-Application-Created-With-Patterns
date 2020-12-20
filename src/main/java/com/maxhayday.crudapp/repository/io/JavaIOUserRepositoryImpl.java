package com.maxhayday.crudapp.repository.io;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.PostBuilderImpl;
import com.maxhayday.crudapp.builder.RegionBuilderImpl;
import com.maxhayday.crudapp.builder.UserBuilderImpl;
import com.maxhayday.crudapp.model.Post;
import com.maxhayday.crudapp.model.Region;
import com.maxhayday.crudapp.model.User;
import com.maxhayday.crudapp.repository.PostRepository;
import com.maxhayday.crudapp.repository.RegionRepository;
import com.maxhayday.crudapp.repository.UserRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOUserRepositoryImpl implements UserRepository {

    private static final Path userPath = Paths.get("/home/max/IdeaProjects/CRUDConsoleApplicationCreatedWithPatterns/src/main/resources/user.txt");
    private String firstName = "", lastName = "", roleStr = "", regionStr = "", postsStr = "", savedStr = "";
    private Long regionId = 0l;
    private static Long countId = 0L;
    private Long idUser = 0L;

    private List<String> list;
    private List<Post> postList;
    private List<Region> regionList;
    private List<User> userList;

    private BufferedReader reader;
    private BufferedWriter writer;
    private ModelDirector director;
    private PostRepository postRepository;
    private RegionRepository regionRepository;

    private User user;
    private Region region;
    private Post post;

    public JavaIOUserRepositoryImpl() throws IOException, ParseException {
        userList = new ArrayList<>();
        director = new ModelDirector();
        postRepository = new JavaIOPostRepositoryImpl();
        regionRepository = new JavaIORegionRepositoryImpl();
        director.setUserBuilder(new UserBuilderImpl());
        director.setRegionBuilder(new RegionBuilderImpl());
        director.setPostBuilder(new PostBuilderImpl());
        userList = getAll();
        for (User u : userList) {
            if (u.getId() > countId) {
                countId = u.getId();
            } else countId = 0L;
        }
    }

    @Override
    public User save(User user) throws IOException {
        List<Post> savedPosts = user.getPosts().stream().map(post -> {
            if (post.getId() == null) {
                try {
                    return postRepository.save(post);
                } catch (IOException e) {
                    System.out.println("Can`t save post");
                }
            }
            return post;
        }).collect(Collectors.toList());
        if (user.getRegion().getId() == null) {
            regionRepository.save(user.getRegion());
        }
        savedStr = (++countId) + "," + user.getName() + "," + user.getLastName() + "," + user.getRole() + "," + savedPosts.stream().map(i -> i.getId()).collect(Collectors.toList()) + ", " + user.getRegion().getId() + "\n";
        Files.write(userPath, savedStr.getBytes(), StandardOpenOption.APPEND);
        return user;
    }

    @Override
    public List<User> getAll() throws IOException, ParseException {
        userList = new ArrayList();
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            postList = new ArrayList<>();
            String str = reader.readLine();
            String[] strings = str.split(",", 5); //id,firstName,lastName,role
            String[] stringsPosts = strings[4].split("\\]")[0].split("[\\p{Punct}\\p{Blank}]");//posts
            String[] stringRegion = strings[4].split("\\]")[1].split("[\\p{Punct}\\p{Blank}]");//region

            if (stringRegion.length > 1) {
                for (String s : stringRegion) {
                    if (s.matches(".*\\d+.*")) {
                        regionId = Long.parseLong(s);
                    } else region = null;
                }
            } else regionId = 0l;
            region = regionRepository.getById(regionId);
            if (region != null) {
                region = director.buildRegion(region.getId(), region.getName());
            }

            idUser = Long.parseLong(strings[0]);
            firstName = strings[1];
            lastName = strings[2];
            roleStr = strings[3];

            if (stringsPosts.length >= 1) {
                for (String s :
                        stringsPosts) {
                    if (s.matches(".*\\d+.*")) {
                        post = postRepository.getById(Long.valueOf(s));
                        if (post != null) {
                            postList.add(post);
                        }
                    }
                }
            }
            user = director.buildUser(idUser, firstName, lastName, postList, region, roleStr);
            userList.add(user);

        }
        reader.close();
        return userList;
    }

    @Override
    public User getById(Long id) throws IOException, ParseException {
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            userList = new ArrayList();
            postList = new ArrayList<>();
            String str = reader.readLine();
            if (Long.parseLong(str.split(",")[0]) == id) {
                String[] strings = str.split(",", 5); //id,firstName,lastName,role
                String[] stringsPosts = strings[4].split("\\]")[0].split("[\\p{Punct}\\p{Blank}]");//posts
                String[] stringRegion = strings[4].split("[^a-zA-Z]");//region


                id = Long.parseLong(strings[0]);
                firstName = strings[1];
                lastName = strings[2];
                roleStr = strings[3];
                for (String s :
                        stringRegion) {
                    if (s.matches("[^\\d]+")) {
                        regionStr = s;
                    }
                }
                regionList = regionRepository.getAll();
                for (Region r : regionList) {
                    String i = r.getName();
                    if (i.equals(regionStr)) {
                        region = director.buildRegion(r.getId(), r.getName());
                    }
                }
                for (String s :
                        stringsPosts) {
                    if (s.matches(".*\\d+.*")) {
                        post = postRepository.getById(Long.valueOf(s));
                        postList.add(post);
                    }
                }
                return user = director.buildUser(id, firstName, lastName, postList, region, roleStr);
            }
        }
        reader.close();
        return user;
    }

    @Override
    public User update(User user) throws IOException, ParseException {
        list = new ArrayList<>();
        postList = postRepository.getAll();
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(userPath);
        for (String s :
                list) {
            if (Long.parseLong(s.split(",")[0]) == user.getId()) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getLastName() + "," + user.getRole() + "," + user.getPosts().stream().map(i -> i.getId()).collect(Collectors.toList()) + ", " + user.getRegion().getId());
            } else {
                writer.write(s);
            }
            writer.newLine();
        }

        writer.close();
        return null;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(userPath);
        for (String s :
                list) {
            if (Long.parseLong(s.split(",")[0]) != id) {
                writer.write(s);
                writer.newLine();
            }
        }
        writer.close();
        --countId;
    }

    @Override
    public void deletePostById(Long id) throws IOException, ParseException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(userPath);
        userList = new ArrayList();
        for (String str : list) {
            postList = new ArrayList<>();
            String[] strings = str.split(",", 5); //id,firstName,lastName,role
            String[] stringsPosts = strings[4].split("\\]")[0].split("[\\p{Punct}\\p{Blank}]");//posts
            String[] stringRegion = strings[4].split("\\]")[1].split("[\\p{Punct}\\p{Blank}]");//region

            if (stringRegion.length > 1) {
                for (String s : stringRegion) {
                    if (s.matches(".*\\d+.*")) {
                        regionId = Long.parseLong(s);
                        region = regionRepository.getById(regionId);
                        region = director.buildRegion(region.getId(), region.getName());
                    } else region = null;
                }
            } else region = null;
            idUser = Long.parseLong(strings[0]);
            firstName = strings[1];
            lastName = strings[2];
            roleStr = strings[3];

            if (stringRegion.length > 1) {
                for (String s :
                        stringsPosts) {
                    if (s.matches(".*\\d+.*")) {
                        if (Long.parseLong(s) != id) {
                            post = postRepository.getById(Long.valueOf(s));
                            post = director.buildPost(post.getId(), post.getContent(), post.getCreated(), post.getUpdated());
                            postList.add(post);
                        }
                    }
                }
            }
            user = director.buildUser(idUser, firstName, lastName, postList, region, roleStr);
            userList.add(user);
            if (user.getRegion() == null) {
                savedStr = user.getId() + "," + user.getName() + "," + user.getLastName() + "," + user.getRole() + "," + user.getPosts().stream().map(i -> i.getId()).collect(Collectors.toList()) + ", " + " " + "\n";
            } else
                savedStr = user.getId() + "," + user.getName() + "," + user.getLastName() + "," + user.getRole() + "," + user.getPosts().stream().map(i -> i.getId()).collect(Collectors.toList()) + ", " + user.getRegion().getId() + "\n";
            Files.write(userPath, savedStr.getBytes(), StandardOpenOption.APPEND);
        }
        writer.close();
    }

    @Override
    public void deleteRegionById(Long id) throws IOException, ParseException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(userPath);
        userList = new ArrayList();
        for (String str : list) {
            postList = new ArrayList<>();
            String[] strings = str.split(",", 5); //id,firstName,lastName,role
            String[] stringsPosts = strings[4].split("\\]")[0].split("[\\p{Punct}\\p{Blank}]");//posts
            String[] stringRegion = strings[4].split("\\]")[1].split("[\\p{Punct}\\p{Blank}]");//region

            for (String s : stringRegion) {
                if (s.matches(".*\\d+.*")) {
                    if (Long.parseLong(s) != id) {
                        regionId = Long.parseLong(s);
                        region = regionRepository.getById(regionId);
                        region = director.buildRegion(region.getId(), region.getName());
                    } else region = null;
                }
            }
            idUser = Long.parseLong(strings[0]);
            firstName = strings[1];
            lastName = strings[2];
            roleStr = strings[3];


            for (String s :
                    stringsPosts) {
                if (s.matches(".*\\d+.*")) {
                    post = postRepository.getById(Long.valueOf(s));
                    post = director.buildPost(post.getId(), post.getContent(), post.getCreated(), post.getUpdated());
                    postList.add(post);
                }
            }
            user = director.buildUser(idUser, firstName, lastName, postList, region, roleStr);
            if (region == null) {
                savedStr = user.getId() + "," + user.getName() + "," + user.getLastName() + "," + user.getRole() + "," + user.getPosts().stream().map(i -> i.getId()).collect(Collectors.toList()) + ", " + " " + "\n";
            } else
                savedStr = user.getId() + "," + user.getName() + "," + user.getLastName() + "," + user.getRole() + "," + user.getPosts().stream().map(i -> i.getId()).collect(Collectors.toList()) + ", " + user.getRegion().getId() + "\n";
            Files.write(userPath, savedStr.getBytes(), StandardOpenOption.APPEND);
        }
        writer.close();
    }
}
