package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "PETROLEUM")
@Entity
public class Petroleum {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @InstanceName
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ECO_CLASS")
    private String ecoClass;

    public String getEcoClass() {
        return ecoClass;
    }

    public void setEcoClass(String ecoClass) {
        this.ecoClass = ecoClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}