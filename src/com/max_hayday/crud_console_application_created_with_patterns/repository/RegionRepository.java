package com.max_hayday.crud_console_application_created_with_patterns.repository;

import com.max_hayday.crud_console_application_created_with_patterns.builder.model.Region;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface RegionRepository {
    Region getById(Long id) throws IOException, ParseException;

    Region save(Region region) throws IOException;

    Region update(Region region) throws IOException;

    List<Region> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;
}
