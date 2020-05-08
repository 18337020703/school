package com.wang.entity;

public class StudentWorkTable {
    private String title;
    private String context;
    private String merchantsId;
    private Double salary;
    private String companyName;
    private String workDate;
    private Integer day;
    private String workAddress;
    private Boolean status;
    private String type;
    private Boolean finish;

    public StudentWorkTable() {
    }

    public StudentWorkTable(String title, String context, String merchantsId, Double salary, String companyName, String workDate, Integer day, String workAddress, String type,Boolean finish,Boolean status) {
        this.title = title;
        this.context = context;
        this.merchantsId = merchantsId;
        this.salary = salary;
        this.companyName = companyName;
        this.workDate = workDate;
        this.day = day;
        this.workAddress = workAddress;
        this.type = type;
        this.finish = finish;
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentWorkTable{" +
                "title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", merchantsId='" + merchantsId + '\'' +
                ", salary=" + salary +
                ", companyName='" + companyName + '\'' +
                ", workDate='" + workDate + '\'' +
                ", day=" + day +
                ", workAddress='" + workAddress + '\'' +
                ", type='" + type + '\'' +
                ", finish='" + finish + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getMerchantsId() {
        return merchantsId;
    }

    public void setMerchantsId(String merchantsId) {
        this.merchantsId = merchantsId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
