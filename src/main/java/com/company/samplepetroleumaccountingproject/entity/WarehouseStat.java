package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

@JmixEntity
public class WarehouseStat {

    private Tank tank;

    private Petroleum petroleum;

    private String ecoClass;

    private Double volume;

    private Double mass;

    private Double density;

    private Double cost;

    private Double price;

    public String getEcoClass() {
        return ecoClass;
    }

    public void setEcoClass(String ecoClass) {
        this.ecoClass = ecoClass;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    public Petroleum getPetroleum() {
        return petroleum;
    }

    public void setPetroleum(Petroleum petroleum) {
        this.petroleum = petroleum;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }
}