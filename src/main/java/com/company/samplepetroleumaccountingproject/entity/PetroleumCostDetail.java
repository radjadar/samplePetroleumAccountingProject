package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "PETROLEUM_COST_DETAIL", indexes = {
        @Index(name = "IDX_PETROLEUM_COST_DETAIL_TANK", columnList = "TANK_ID"),
        @Index(name = "IDX_PETROLEUM_COST_DETAIL_PETROLEUM", columnList = "PETROLEUM_ID"),
        @Index(name = "IDX_PETROLEUM_COST_DETAIL_PETROLEUM_OPERATION", columnList = "PETROLEUM_OPERATION_ID")
})
@Entity
public class PetroleumCostDetail {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "DATE_")
    private LocalDateTime date;

    @Column(name = "MASS")
    private Double mass;

    @Column(name = "COST")
    private Double cost;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PETROLEUM_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Petroleum petroleum;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "TANK_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tank tank;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PETROLEUM_OPERATION_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PetroleumOperation petroleumOperation;

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public PetroleumOperation getPetroleumOperation() {
        return petroleumOperation;
    }

    public void setPetroleumOperation(PetroleumOperation petroleumOperation) {
        this.petroleumOperation = petroleumOperation;
    }

    public Petroleum getPetroleum() {
        return petroleum;
    }

    public void setPetroleum(Petroleum petroleum) {
        this.petroleum = petroleum;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}