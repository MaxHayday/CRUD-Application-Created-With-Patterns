package com.maxhayday.crudapp.repository.io;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.RegionBuilderImpl;
import com.maxhayday.crudapp.model.Region;
import com.maxhayday.crudapp.repository.RegionRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class JavaIORegionRepositoryImpl implements RegionRepository {
    private static final Path regionPath = Paths.get("/home/max/IdeaProjects/CRUDConsoleApplicationCreatedWithPatterns/src/main/resources/region.txt");
    private static Long countId = 0L;
    private List<Region> regionList;
    private List<String> list;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Region region;
    private ModelDirector modelDirector;


    public JavaIORegionRepositoryImpl() throws IOException {
        modelDirector = new ModelDirector();
        modelDirector.setRegionBuilder(new RegionBuilderImpl());
        regionList = getAll();
        for (Region region :
                regionList) {
            if (region.getId() > countId) {
                countId = region.getId();
            } else countId = 0L;
        }
    }

    @Override
    public Region save(Region region) throws IOException {
        if (region.getId() == null) {
            region.setId(++countId);
        }
        String regionStr = region.getId() + "," + region.getName() + "/" + "\n";
        Files.write(regionPath, regionStr.getBytes(), StandardOpenOption.APPEND);
        return region;
    }


    @Override
    public Region getById(Long id) throws IOException {
        reader = Files.newBufferedReader(regionPath);
        list = new ArrayList<>();
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        for (String s :
                list) {
            if (!(s.isEmpty())) {
                if (Long.parseLong(s.split(",")[0]) == id) {
                    region = modelDirector.buildRegion(id, s.split(",")[1]);
                    return region;
                }
            } else break;
        }
        return null;
    }

    @Override
    public Region update(Region region) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(regionPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(regionPath);
        for (String s : list) {
            if (Long.parseLong(s.split(",")[0]) == region.getId()) {
                writer.write(region.getId() + "," + region.getName() + "/");
            } else {
                writer.write(s);
            }
            writer.newLine();
        }
        writer.close();
        return null;
    }

    @Override
    public List<Region> getAll() throws IOException {

        regionList = new ArrayList();
        reader = Files.newBufferedReader(regionPath);
        while (reader.ready()) {
            String line = reader.readLine();
            if (line.length() != 0) {
                String[] ar = line.split(",");
                Long id = Long.parseLong(ar[0]);
                String name = ar[1].split("\\p{Punct}")[0];
                region = modelDirector.buildRegion(id, name);
                regionList.add(region);
            }
        }
        reader.close();
        return regionList;
    }

    @Override
    public void deleteById(Long id) throws IOException {
        list = new ArrayList<>();
        reader = Files.newBufferedReader(regionPath);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        writer = Files.newBufferedWriter(regionPath);
        for (String s :
                list) {
            if (Long.parseLong(s.split(",")[0]) != id) {
                writer.write(s);
                writer.newLine();
            }
        }
        writer.close();
    }
}
