package com.maxhayday.crudapp.controller;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.RegionBuilderImpl;
import com.maxhayday.crudapp.model.Region;
import com.maxhayday.crudapp.repository.RegionRepository;
import com.maxhayday.crudapp.repository.UserRepository;
import com.maxhayday.crudapp.repository.io.JavaIORegionRepositoryImpl;
import com.maxhayday.crudapp.repository.io.JavaIOUserRepositoryImpl;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class RegionController {
    private RegionRepository repository;
    private UserRepository userRepository;
    private ModelDirector modelDirector;
    private Region region;


    public RegionController() {
        modelDirector = new ModelDirector();
        modelDirector.setRegionBuilder(new RegionBuilderImpl());
        try {
            repository = new JavaIORegionRepositoryImpl();
            userRepository = new JavaIOUserRepositoryImpl();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void save(Long id, String name) {
        region = modelDirector.buildRegion(id, name);
        try {
            repository.save(region);
        } catch (IOException e) {
            System.out.println("Wrong id of name.");
        }
    }

    public List<Region> getAll() {
        try {
            return repository.getAll();
        } catch (IOException | ParseException e) {
            System.out.println("You haven`t regions.");
        }
        return null;
    }

    public void update(Long id, String name) {
        region = modelDirector.buildRegion(id, name);
        try {
            repository.update(region);
        } catch (IOException e) {
            System.out.println("Can`t update region.");
        }
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
            userRepository.deleteRegionById(id);
        } catch (IOException | ParseException e) {
            System.out.println("Can`t delete region.");
        }

    }

    public Region getById(Long id) {
        try {
            return repository.getById(id);
        } catch (IOException | ParseException e) {
            System.out.println("Wrong id.");
        }
        return null;
    }
}
