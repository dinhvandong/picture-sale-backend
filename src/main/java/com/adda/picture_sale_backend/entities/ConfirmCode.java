package com.adda.picture_sale_backend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "confirm_code")
public class ConfirmCode {
    @Id
    private  Long id;

    @Transient
    public static final String SEQUENCE_NAME = "confirm_code_sequence";

    @Transient
    public static final int STATUS_CONFIRM_OK = 2;

    @Transient
    public static final int STATUS_CONFIRM_PENDING = 1;

    private Long userID;

    private String email;


    private String secureCode;

    private String pathRandom;

    private Long createdTime;

    private int status = 1;

    private String type = REGISTER_NEW;


    @Transient
    public static final String FORGOT_PASSWORD = "forgot_password";

    @Transient
    public static final String REGISTER_NEW = "register_new";

    public String getPathRandom() {
        return pathRandom;
    }

    public void setPathRandom(String pathRandom) {
        this.pathRandom = pathRandom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
