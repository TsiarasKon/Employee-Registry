package com.unisystems.registry;

import java.util.Date;

public class GenericError {
    private int code;
    private String title;
    private String desc;
    private Date timestamp;

    public GenericError(int code, String title, String desc) {
        this.code = code;
        this.title = title;
        this.desc = desc;
        this.timestamp = new Date();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
