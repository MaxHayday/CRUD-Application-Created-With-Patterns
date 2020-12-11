package com.maxhayday.crudapp.builder;

import com.maxhayday.crudapp.model.Region;

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
