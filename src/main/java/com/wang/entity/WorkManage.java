package com.wang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class WorkManage implements Serializable {
    private Integer id;
    private Integer workId;
    private Integer StudentId;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date greatTime;

    public WorkManage() {
    }

    public WorkManage(Integer id, Integer workId, Integer studentId, Date greatTime) {
        this.id = id;
        this.workId = workId;
        StudentId = studentId;
        this.greatTime = greatTime;
    }

    @Override
    public String toString() {
        return "WorkManageDao{" +
                "id=" + id +
                ", workId=" + workId +
                ", StudentId=" + StudentId +
                ", greatTime=" + greatTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getStudentId() {
        return StudentId;
    }

    public void setStudentId(Integer studentId) {
        StudentId = studentId;
    }

    public Date getGreatTime() {
        return greatTime;
    }

    public void setGreatTime(Date greatTime) {
        this.greatTime = greatTime;
    }
}
