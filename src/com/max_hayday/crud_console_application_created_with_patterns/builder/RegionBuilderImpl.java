package com.max_hayday.crud_console_application_created_with_patterns.builder;

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
