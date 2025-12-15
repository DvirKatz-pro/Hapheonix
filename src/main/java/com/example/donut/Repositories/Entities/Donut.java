package com.example.donut.Repositories.Entities;

import java.util.List;

public class Donut {
    String donutId;

    String donutName;
    String donutDescription;
    float donutPrice;

    public Donut(String donutId, String donutName, String donutDescription, float donutPrice) {
        this.donutId = donutId;
        this.donutName = donutName;
        this.donutDescription = donutDescription;
        this.donutPrice = donutPrice;
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

}
