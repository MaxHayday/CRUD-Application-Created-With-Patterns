package com.maxhayday.crudapp.repository.io;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.UserBuilderImpl;
import com.maxhayday.crudapp.model.User;
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

public class JavaIOUserRepositoryImpl implements UserRepository {
    private static final Path userPath = Paths.get("/home/max/IdeaProjects/CRUDConsoleApplicationCreatedWithPatterns/src/main/resources/user.txt");
    private static Long countId = 0L;
    private List<String> list;
    private BufferedReader reader;
    private BufferedWriter writer;
    private ModelDirector director;
    private User user;

    public JavaIOUserRepositoryImpl() throws IOException, ParseException {
        director = new ModelDirector();
        director.setUserBuilder(new UserBuilderImpl());
        List<User> userList = getAll();
        for (User u :
                userList) {
            if (u.getId() > countId) {
                countId = u.getId();
            } else countId = 0L;
        }

    }

    @Override
    public User getById(Long id) throws IOException {
        reader = Files.newBufferedReader(userPath);
        list = new ArrayList<>();
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        for (String s :
                list) {
            if (Long.parseLong(s.split("\\.")[0]) == id) {
                String[] strings = s.split("\\s+|\\.\\s*");
                String role = s.split("\\s+|\\.\\s*")[3];
                user = director.buildUser(id, strings[1], strings[2], role);
                return user;
            }
        }
        return null;
    }

    @Override
    public User save(User user) throws IOException {
        String regionStr = (++countId) + "." + user.getName() + " " + user.getLastName() + " " + user.getRole() + "\n";
        Files.write(userPath, regionStr.getBytes(), StandardOpenOption.APPEND);
        return user;
    }

    @Override
    public User update(User user) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(userPath);
        for (String s :
                list) {
            if (Long.parseLong(s.split("\\.")[0]) == user.getId()) {
                writer.write(user.getId() + "." + user.getName() + " " + user.getLastName() + " " + user.getRole());
            } else {
                writer.write(s);
            }
            writer.newLine();
        }

        writer.close();
        return null;
    }

    @Override
    public User update(List<User> t) throws IOException {
        return null;
    }

    @Override
    public List<User> getAll() throws IOException, ParseException {
        List<User> userList = new ArrayList();
        Long id;
        String firstName, lastName, role;
        reader = Files.newBufferedReader(userPath);
        while (reader.ready()) {
            String[] str = reader.readLine().split("\\s+|\\.\\s*");
            if (str.length != 0 && !(str[0].isEmpty())) {
                id = Long.parseLong(str[0]);
                firstName = str[1];
                lastName = str[2];
                role = str[3];
                user = director.buildUser(id, firstName, lastName, role);
                userList.add(user);
            }
        }
        reader.close();
        return userList;
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
            if (Long.parseLong(s.split("\\.")[0]) != id) {
                writer.write(s);
                writer.newLine();
            }
        }
        writer.close();
        --countId;
    }
}
