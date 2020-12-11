package com.maxhayday.crudapp.builder;

public class RegionBuilderImpl extends RegionBuilder {
    @Override
    void buildID(Long id) {
        region.setId(id);
    }

    @Override
    void buildName(String name) {
        region.setName(name);
    }
}
