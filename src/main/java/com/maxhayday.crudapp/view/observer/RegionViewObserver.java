package com.maxhayday.crudapp.view.observer;

import com.maxhayday.crudapp.builder.ModelDirector;
import com.maxhayday.crudapp.builder.RegionBuilderImpl;
import com.maxhayday.crudapp.model.Region;
import com.maxhayday.crudapp.controller.RegionController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.List;

public class RegionViewObserver implements ViewObserver {
    private RegionController controller;
    private ModelDirector director;
    private String data;
    private List<Region> regionsList;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Region region;
    private static Long id = 0L;

    public RegionViewObserver() throws IOException, ParseException {
        controller = new RegionController();
        director = new ModelDirector();
        director.setRegionBuilder(new RegionBuilderImpl());
    }

    @Override
    public void create() {
        try {
            System.out.println("Write a region: ");
            data = reader.readLine();
            if (!(data.isEmpty() || data.matches("[0-9]") || data.matches("[^\\w]"))) {
                region = director.buildRegion(id, data);
                controller.save(region);
            } else return;
        } catch (IOException e) {
            System.out.println("Please wright correct region;");
        }
    }

    @Override
    public void update(Long id) {
        try {
            regionsList = controller.getAll();
            if (regionsList.isEmpty()) return;
            System.out.println("Write new region: ");
            data = reader.readLine();
            region = director.buildRegion(id, data);
            controller.update(region);
        } catch (IOException | ParseException | NumberFormatException exception) {
            System.out.println("Wrong id or name.");
        }
    }

    @Override
    public void getById(Long id) {
        try {
            regionsList = controller.getAll();
            if (regionsList.isEmpty()) return;
            region = controller.getById(id);
            System.out.printf("%-20s", region.getName());
        } catch (IOException | ParseException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }

    @Override
    public void getAll() {
        try {
            regionsList = controller.getAll();
            if (regionsList.isEmpty()) {
                return;
            }
//            for (Region i :
//                    regionsList) {
//                System.out.printf("%-25s", i.getName());
//            }
        } catch (IOException | ParseException e) {
            System.out.println("You have not regions.");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            controller.deleteById(id);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Write correct id.");
        }
    }
}
