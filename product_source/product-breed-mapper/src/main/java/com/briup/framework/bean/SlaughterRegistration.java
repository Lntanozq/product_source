package com.briup.framework.bean;

public class SlaughterRegistration {
    private Integer srnId;

    private String srnTime;

    private String srnBatchId;

    private Integer srnGroupId;

    public Integer getSrnId() {
        return srnId;
    }

    public void setSrnId(Integer srnId) {
        this.srnId = srnId;
    }

    public String getSrnTime() {
        return srnTime;
    }

    public void setSrnTime(String srnTime) {
        this.srnTime = srnTime;
    }

    public String getSrnBatchId() {
        return srnBatchId;
    }

    public void setSrnBatchId(String srnBatchId) {
        this.srnBatchId = srnBatchId;
    }

    public Integer getSrnGroupId() {
        return srnGroupId;
    }

    public void setSrnGroupId(Integer srnGroupId) {
        this.srnGroupId = srnGroupId;
    }
}