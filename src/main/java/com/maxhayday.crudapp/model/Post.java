package com.maxhayday.crudapp.model;

public class Post extends AbstractBaseEntity {
    private String content;
    private String created;
    private String updated;

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
