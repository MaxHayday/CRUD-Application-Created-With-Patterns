package com.max_hayday.crud_console_application_created_with_patterns.controller;

import com.max_hayday.crud_console_application_created_with_patterns.builder.model.Region;
import com.max_hayday.crud_console_application_created_with_patterns.repository.JavaIORegionRepositoryImpl;
import com.max_hayday.crud_console_application_created_with_patterns.repository.RegionRepository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class RegionController {
    private RegionRepository repository;

    public RegionController() throws IOException, ParseException {
        repository = new JavaIORegionRepositoryImpl();
    }

    public void save(Region region) throws IOException {
        repository.save(region);
    }

    public List<Region> getAll() throws IOException, ParseException {
        return repository.getAll();
    }

    public void update(Region region) throws IOException {
        repository.update(region);
    }

    public void deleteById(Long id) throws IOException {
        repository.deleteById(id);
    }

    public Region getById(Long id) throws IOException, ParseException {
        return repository.getById(id);
    }
}
