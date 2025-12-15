package com.example.donut.Controllers.DTO;

import com.example.donut.Repositories.Entities.Donut;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class DonutDTO {
    @NotBlank
    String donutId;
    @NotBlank
    String donutName;
    @NotBlank
    String donutDescription;
    @Positive
    float donutPrice;
    String returnMessage;

    public DonutDTO() {}

    public DonutDTO(String donutId,String donutName, String donutDescription, float donutPrice) {
        this.donutId = donutId;
        this.donutName = donutName;
        this.donutDescription = donutDescription;
        this.donutPrice = donutPrice;
    }

    public DonutDTO(Donut donut) {
        this.donutId = donut.getDonutId();
        this.donutName = donut.getDonutName();
        this.donutDescription = donut.getDonutDescription();
        this.donutPrice = donut.getDonutPrice();
    }

    public String getDonutId() {
        return donutId;
    }

    public void setDonutId(String donutId) {
        this.donutId = donutId;
    }

    public String getDonutName() {
        return donutName;
    }

    public void setDonutName(String donutName) {
        this.donutName = donutName;
    }

    public String getDonutDescription() {
        return donutDescription;
    }

    public void setDonutDescription(String donutDescription) {
        this.donutDescription = donutDescription;
    }

    public float getDonutPrice() {
        return donutPrice;
    }

    public void setDonutPrice(float donutPrice) {
        this.donutPrice = donutPrice;
    }

    public String getReturnMessage() {return returnMessage;}

    public void setReturnMessage(String returnMessage) {this.returnMessage = returnMessage;}
}
