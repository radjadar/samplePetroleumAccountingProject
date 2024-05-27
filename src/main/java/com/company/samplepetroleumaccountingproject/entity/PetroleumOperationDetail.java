package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

@JmixEntity
@Table(name = "PETROLEUM_OPERATION_DETAIL", indexes = {
        @Index(name = "IDX_PETROLEUM_OPERATION_DETAIL_PETROLEUM", columnList = "PETROLEUM_ID"),
        @Index(name = "IDX_PETROLEUM_OPERATION_DETAIL_TANK", columnList = "TANK_ID"),
        @Index(name = "IDX_PETROLEUM_OPERATION_DETAIL_PETROLEUM_OPERATION", columnList = "PETROLEUM_OPERATION_ID")
})
@Entity
public class PetroleumOperationDetail {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @JoinColumn(name = "PETROLEUM_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Petroleum petroleum;

    @Positive
    @NotNull
    @Column(name = "DENSITY", nullable = false)
    private Double density;

    @Positive
    @NotNull
    @Column(name = "MASS", nullable = false)
    private Double mass;

    @Positive
    @NotNull
    @Column(name = "PRICE", nullable = false)
    private Double price;

    @NotNull
    @JoinColumn(name = "TANK_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Tank tank;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PETROLEUM_OPERATION_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PetroleumOperation petroleumOperation;

    @DependsOnProperties({"mass", "density"})
    @JmixProperty
    public Double getVolume() {
        if(getMass() != null && getDensity() != null && getDensity() != 0) {
            return getMass() / getDensity();
        } else {return null;}
    }

    @DependsOnProperties({"mass", "price"})
    @JmixProperty
    public Double getCost() {
        if(getMass() != null && getPrice() != null) {
            return getMass() * getPrice();
        } else {return null;}
    }

    public PetroleumOperation getPetroleumOperation() {
        return petroleumOperation;
    }

    public void setPetroleumOperation(PetroleumOperation petroleumOperation) {
        this.petroleumOperation = petroleumOperation;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}