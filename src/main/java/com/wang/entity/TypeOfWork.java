package com.wang.entity;

import java.io.Serializable;

public class TypeOfWork implements Serializable {
    private Integer id;
    private String name;

    public TypeOfWork() {
    }

    public TypeOfWork(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TypeOfWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
