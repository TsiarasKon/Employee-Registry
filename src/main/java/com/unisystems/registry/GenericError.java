package com.unisystems.registry;

import java.time.LocalDate;
import java.util.Date;

public class GenericError
{
    private Date timeStamp;
    private int code;
    private String title;
    private String desc;

    public GenericError(Date timeStamp,int code , String title, String desc)
    {
        this.timeStamp = timeStamp;
        this.code = code;
        this.title = title;
        this.desc = desc;
    }

    public GenericError(int code, String title, String desc) {
        this.code = code;
        this.title = title;
        this.desc = desc;
    }


    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
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
}
