package com.wang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class PartTimeWork implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private Double salary;
    private String workAddress;
    private Boolean workStatus;
    private Boolean workRequest;
    private String workDate;
    private String companyId;
    private String typeOfWork;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date releaseDate;
    private Boolean thisStatus;

    public PartTimeWork() {
    }

    public PartTimeWork(Integer id, String title, String content, Double salary, String workAddress, Boolean workStatus, Boolean workRequest, String workDate, String companyId, String typeOfWork, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.salary = salary;
        this.workAddress = workAddress;
        this.workStatus = workStatus;
        this.workRequest = workRequest;
        this.workDate = workDate;
        this.companyId = companyId;
        this.typeOfWork = typeOfWork;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "PartTimeWork{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", salary=" + salary +
                ", workAddress='" + workAddress + '\'' +
                ", workStatus=" + workStatus +
                ", workRequest=" + workRequest +
                ", workDate=" + workDate +
                ", companyId=" + companyId +
                ", typeOfWork='" + typeOfWork + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    public Boolean getThisStatus() {
        return thisStatus;
    }

    public void setThisStatus(Boolean thisStatus) {
        this.thisStatus = thisStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public Boolean getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Boolean workStatus) {
        this.workStatus = workStatus;
    }

    public Boolean getWorkRequest() {
        return workRequest;
    }

    public void setWorkRequest(Boolean workRequest) {
        this.workRequest = workRequest;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
