package com.adda.picture_sale_backend.dto;

import java.util.List;
public class TransactionResponse {
        private  Long id;
        private String name;
        private Long userId;

        private  String username;
        private String note;
        private Long money;
        private String withPerson;
        private String group;

        private Long groupID;
        private String category;

        private  Long categoryID;
        private  Long createdDate;
        private List<String> listImages;
        private int active;

    public Long getId() {
        return id;
    }

    public Long getGroupID() {
        return groupID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getWithPerson() {
        return withPerson;
    }

    public void setWithPerson(String withPerson) {
        this.withPerson = withPerson;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getListImages() {
        return listImages;
    }

    public void setListImages(List<String> listImages) {
        this.listImages = listImages;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


}
