package com.wang.entity;

public class StudentList {
    private String id;
    private String nickname;
    private String sex;
    private String phone;
    private String bir;
    private String university;
    private String department;
    private String profession;
    private Boolean status;
    private Boolean finish;
    private Integer day;

    public StudentList() {
    }

    public StudentList(String id, String nickname, String sex, String phone, String bir, String university, String department, String profession, Boolean status, Boolean finish, Integer day) {
        this.id = id;
        this.nickname = nickname;
        this.sex = sex;
        this.phone = phone;
        this.bir = bir;
        this.university = university;
        this.department = department;
        this.profession = profession;
        this.status = status;
        this.finish = finish;
        this.day = day;
    }

    @Override
    public String toString() {
        return "StudentList{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", bir='" + bir + '\'' +
                ", university='" + university + '\'' +
                ", department='" + department + '\'' +
                ", profession='" + profession + '\'' +
                ", status=" + status +
                ", finish=" + finish +
                ", day=" + day +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBir() {
        return bir;
    }

    public void setBir(String bir) {
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

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
