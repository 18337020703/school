package com.wang.entity;

public class VoEntity {
    private String typeOfWork;
    private Boolean workStatus;
    private Boolean workRequest;
    private Boolean finish;
    private Integer start;
    private Integer size;
    private String companyId;
    private String studentId;
    private Integer partTimeWorkId;

    public VoEntity() {
    }

    @Override
    public String toString() {
        return "VoEntity{" +
                "typeOfWork='" + typeOfWork + '\'' +
                ", workStatus=" + workStatus +
                ", workRequest=" + workRequest +
                ", start=" + start +
                ", size=" + size +
                '}';
    }

    public VoEntity(String typeOfWork, Boolean workStatus, Boolean finish,Boolean workRequest, Integer start, Integer size,String companyId,String studentId,Integer partTimeWorkId) {
        this.typeOfWork = typeOfWork;
        this.workStatus = workStatus;
        this.workRequest = workRequest;
        this.start = start;
        this.size = size;
        this.companyId = companyId;
        this.studentId = studentId;
        this.partTimeWorkId = partTimeWorkId;
        this.finish = finish;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Integer getPartTimeWorkId() {
        return partTimeWorkId;
    }

    public void setPartTimeWorkId(Integer partTimeWorkId) {
        this.partTimeWorkId = partTimeWorkId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
