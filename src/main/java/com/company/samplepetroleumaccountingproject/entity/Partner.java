package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PARTNER")
@Entity
public class Partner {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @InstanceName
    @Column(name = "SHORT_NAME", nullable = false)
    private String shortName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "INN")
    private String inn;

    @Column(name = "KPP")
    private String kpp;

    @Column(name = "LEGAL_ADDRESS")
    private String legalAddress;

    @Column(name = "PHYSICAL_ADDRESS")
    private String physicalAddress;

    @Composition
    @OneToMany(mappedBy = "partner")
    private List<Person> contactPersons;
    @Composition
    @OneToMany(mappedBy = "partner")
    private List<BankAccount> operatingAccounts;
    @JmixProperty
    @Transient
    private Person ceo;

    @JmixProperty(mandatory = true)
    @Transient
    @NotNull
    private BankAccount operatingAccount;

    public List<BankAccount> getOperatingAccounts() {
        return operatingAccounts;
    }

    public void setOperatingAccounts(List<BankAccount> operatingAccounts) {
        this.operatingAccounts = operatingAccounts;
    }

    public List<Person> getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(List<Person> contactPersons) {
        this.contactPersons = contactPersons;
    }

    public BankAccount getOperatingAccount() {
        return operatingAccount;
    }

    public void setOperatingAccount(BankAccount operatingAccount) {
        this.operatingAccount = operatingAccount;
    }

    public Person getCeo() {
        return ceo;
    }

    public void setCeo(Person ceo) {
        this.ceo = ceo;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}