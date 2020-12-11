package com.maxhayday.crudapp.model;

public abstract class AbstractBaseEntity {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
