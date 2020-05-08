package com.wang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Deal implements Serializable {
    private String id;
    private String sid;
    private Integer pid;
    private Integer day;
    private Boolean status;
    private Boolean finish;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date date;

    public Deal() {
    }

    public Deal(String id, String sid, Integer pid, Integer day, Boolean status, Boolean finish, Date date) {
        this.id = id;
        this.sid = sid;
        this.pid = pid;
        this.day = day;
        this.status = status;
        this.finish = finish;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id='" + id + '\'' +
                ", sid='" + sid + '\'' +
                ", pid=" + pid +
                ", day=" + day +
                ", status=" + status +
                ", finish=" + finish +
                ", date=" + date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

