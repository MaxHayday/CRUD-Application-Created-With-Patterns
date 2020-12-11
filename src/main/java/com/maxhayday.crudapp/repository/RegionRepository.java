package com.maxhayday.crudapp.repository;

import com.maxhayday.crudapp.model.Region;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface RegionRepository extends GenericRepository<Region,Long>{
    Region getById(Long id) throws IOException, ParseException;

    Region save(Region region) throws IOException;

    Region update(Region region) throws IOException;

    List<Region> getAll() throws IOException, ParseException;

    void deleteById(Long id) throws IOException;
}
