package com.adda.picture_sale_backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.List;

public class Artist {

    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "artist_sequence";

    private String name;

    private String journey;

    private List<String> awards;

    private List<String> activities;

    private String avatar;

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String getJourney() {
        return journey;
    }

    public void setJourney(String journey) {
        this.journey = journey;
    }

    public List<String> getAwards() {
        return awards;
    }

    public void setAwards(List<String> awards) {
        this.awards = awards;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
