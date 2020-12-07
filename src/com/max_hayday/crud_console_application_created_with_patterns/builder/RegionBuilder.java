package com.max_hayday.crud_console_application_created_with_patterns.builder;

import com.max_hayday.crud_console_application_created_with_patterns.builder.model.Region;

public abstract class RegionBuilder {
    Region region;

    void createRegion() {
        region = new Region();
    }

    abstract void buildID(Long id);

    abstract void buildName(String name);

    Region getRegion() {
        return region;
    }

}
