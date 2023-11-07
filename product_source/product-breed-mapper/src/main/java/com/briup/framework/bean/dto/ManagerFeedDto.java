package com.briup.framework.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("饲料模型")
public class ManagerFeedDto {
    @ApiModelProperty(value = "饲料id",example = "1")
    private Integer fId;
    @ApiModelProperty(value = "饲料名称",example = "正大猪饲料")
    private String fName;
    @ApiModelProperty(value = "饲料供应商",example = "云南优鼎生物科技有限公司")
    private String fSupplier;
    @ApiModelProperty(value = "饲料价格",example = "100")
    private Double fPrice;
    @ApiModelProperty(value = "饲料类型",example = "蛋白质饲料")
    private String fType;
    @ApiModelProperty(value = "饲料图片",example = "M00/00/00/rBBkoGJFL4qAHTETAAHGjVlOoX0498.jpg")
    private String fImg;
    @ApiModelProperty(value = "饲料描述",example = "动物饲料用作肥料")
    private String fDesc;
    @ApiModelProperty(value = "饲料创建时间",example = "2022-03-24")
    private String fTime;
    @ApiModelProperty(hidden = true)
    private Integer fDelete;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfSupplier() {
        return fSupplier;
    }

    public void setfSupplier(String fSupplier) {
        this.fSupplier = fSupplier;
    }

    public Double getfPrice() {
        return fPrice;
    }

    public void setfPrice(Double fPrice) {
        this.fPrice = fPrice;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public String getfImg() {
        return fImg;
    }

    public void setfImg(String fImg) {
        this.fImg = fImg;
    }

    public String getfDesc() {
        return fDesc;
    }

    public void setfDesc(String fDesc) {
        this.fDesc = fDesc;
    }

    public String getfTime() {
        return fTime;
    }

    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    public Integer getfDelete() {
        return fDelete;
    }

    public void setfDelete(Integer fDelete) {
        this.fDelete = fDelete;
    }
}