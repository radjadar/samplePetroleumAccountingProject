package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "PERSON", indexes = {
        @Index(name = "IDX_PERSON_PARTNER", columnList = "PARTNER_ID")
})
@Entity
public class Person {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @InstanceName
    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "CONTACTS")
    private String contacts;

    @Column(name = "CEO")
    private Boolean ceo;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PARTNER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Partner partner;

    public Boolean getCeo() {
        return ceo;
    }

    public void setCeo(Boolean ceo) {
        this.ceo = ceo;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}