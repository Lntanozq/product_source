package com.briup.framework.bean;

public class InoculationRecord {
    private Integer irId;

    private String irTime;

    private String irCapacity;

    private String irUinoculationId;

    private String irVaccinesId;

    private String irAnimalId;

    public Integer getIrId() {
        return irId;
    }

    public void setIrId(Integer irId) {
        this.irId = irId;
    }

    public String getIrTime() {
        return irTime;
    }

    public void setIrTime(String irTime) {
        this.irTime = irTime;
    }

    public String getIrCapacity() {
        return irCapacity;
    }

    public void setIrCapacity(String irCapacity) {
        this.irCapacity = irCapacity;
    }

    public String getIrUinoculationId() {
        return irUinoculationId;
    }

    public void setIrUinoculationId(String irUinoculationId) {
        this.irUinoculationId = irUinoculationId;
    }

    public String getIrVaccinesId() {
        return irVaccinesId;
    }

    public void setIrVaccinesId(String irVaccinesId) {
        this.irVaccinesId = irVaccinesId;
    }

    public String getIrAnimalId() {
        return irAnimalId;
    }

    public void setIrAnimalId(String irAnimalId) {
        this.irAnimalId = irAnimalId;
    }
}