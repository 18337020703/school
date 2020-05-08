package com.wang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Session implements Serializable {

    private String id;
    private String username;
    private String password;
    private String phone;
    private String nickname;
    private String sex;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date bir;
    private String university;
    private String department;
    private String profession;
    private String companyName;
    private String companyPhone;
    private String getCompanyAddress;
    private String companyIntroduction;
    private Boolean enabled;
    private Boolean locked;

    public Session() {
    }

    public Session(String id, String username, String password, String phone, String nickname, String sex, Date bir, String university, String department, String profession, String companyName, String companyPhone, String getCompanyAddress, String companyIntroduction, Boolean enabled, Boolean locked) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.nickname = nickname;
        this.sex = sex;
        this.bir = bir;
        this.university = university;
        this.department = department;
        this.profession = profession;
        this.companyName = companyName;
        this.companyPhone = companyPhone;
        this.getCompanyAddress = getCompanyAddress;
        this.companyIntroduction = companyIntroduction;
        this.enabled = enabled;
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", bir=" + bir +
                ", university='" + university + '\'' +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", getCompanyAddress='" + getCompanyAddress + '\'' +
                ", companyIntroduction='" + companyIntroduction + '\'' +
                ", enabled=" + enabled +
                ", locked=" + locked +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getGetCompanyAddress() {
        return getCompanyAddress;
    }

    public void setGetCompanyAddress(String getCompanyAddress) {
        this.getCompanyAddress = getCompanyAddress;
    }

    public String getCompanyIntroduction() {
        return companyIntroduction;
    }

    public void setCompanyIntroduction(String companyIntroduction) {
        this.companyIntroduction = companyIntroduction;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
