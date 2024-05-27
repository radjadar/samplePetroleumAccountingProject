package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "PETROLEUM_PRICE_DETAIL", indexes = {
//        @Index(name = "IDX_PETROLEUM_PRICE_DETAIL_TANK", columnList = "TANK_ID"),
        @Index(name = "IDX_PETROLEUM_PRICE_DETAIL_PETROLEUM", columnList = "PETROLEUM_ID"),
        @Index(name = "IDX_PETROLEUM_PRICE_DETAIL_PETROLEUM_OPERATION", columnList = "PETROLEUM_OPERATION_ID")
})
@Entity
public class PetroleumPriceDetail {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "DATE_")
    private LocalDate date;

    @Column(name = "PRICE")
    private Double price;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PETROLEUM_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Petroleum petroleum;
//
//    @OnDeleteInverse(DeletePolicy.CASCADE)
//    @JoinColumn(name = "TANK_ID")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Tank tank;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PETROLEUM_OPERATION_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PetroleumOperation petroleumOperation;
//
//    public Tank getTank() {
//        return tank;
//    }
//
//    public void setTank(Tank tank) {
//        this.tank = tank;
//    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}