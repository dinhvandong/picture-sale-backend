package com.adda.picture_sale_backend.entities;
import org.springframework.data.annotation.Transient;

//@Document(collection = "categories")

public class Category {
    private Long id;
    @Transient
    public static final String SEQUENCE_NAME = "category_sequence";
    private String name;

    private boolean active;
    private Long createdDate;

    public String desc;

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDesc() {
        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}
