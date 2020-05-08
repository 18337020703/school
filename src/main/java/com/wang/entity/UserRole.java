package com.wang.entity;

public class UserRole {
    private Integer id;
    private String uid;
    private Integer rid;

    public UserRole() {
    }

    public UserRole(Integer id, String uid, Integer rid) {
        this.id = id;
        this.uid = uid;
        this.rid = rid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }
}
