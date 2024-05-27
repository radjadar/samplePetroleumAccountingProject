package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "TANK", indexes = {
        @Index(name = "IDX_TANK_PETROLEUM", columnList = "PETROLEUM_ID")
})
@Entity
public class Tank {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @InstanceName
    @Column(name = "MARK", nullable = false)
    private String mark;

    @NotNull
    @JoinColumn(name = "PETROLEUM_ID", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private Petroleum petroleum;

    @Positive
    @NotNull
    @Column(name = "MAX_VOLUME", nullable = false)
    private Double maxVolume;

    @Positive
    @NotNull
    @Column(name = "MIN_VOLUME", nullable = false)
    private Double minVolume;

    @Composition
    @OneToMany(mappedBy = "tank")
    private List<TankOperationDetail> operationDetails;

    public List<TankOperationDetail> getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(List<TankOperationDetail> operationDetails) {
        this.operationDetails = operationDetails;
    }

    public Double getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(Double minVolume) {
        this.minVolume = minVolume;
    }

    public Double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(Double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public Petroleum getPetroleum() {
        return petroleum;
    }

    public void setPetroleum(Petroleum petroleum) {
        this.petroleum = petroleum;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}