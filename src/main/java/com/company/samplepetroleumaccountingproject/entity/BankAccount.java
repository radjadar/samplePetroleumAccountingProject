package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "BANK_ACCOUNT", indexes = {
        @Index(name = "IDX_BANK_ACCOUNT_PARTNER", columnList = "PARTNER_ID")
})
@Entity
public class BankAccount {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;

    @Column(name = "CORRESPONDENT_ACCOUNT")
    private String correspondentAccount;

    @NotNull
    @Column(name = "BANK", nullable = false)
    private String bank;

    @Column(name = "BIC")
    private String bic;

    @Column(name = "INN")
    private String inn;

    @Column(name = "OPERATING_ACCOUNT")
    private Boolean operatingAccount;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PARTNER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Partner partner;

    public Boolean getOperatingAccount() {
        return operatingAccount;
    }

    public void setOperatingAccount(Boolean operatingAccount) {
        this.operatingAccount = operatingAccount;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCorrespondentAccount() {
        return correspondentAccount;
    }

    public void setCorrespondentAccount(String correspondentAccount) {
        this.correspondentAccount = correspondentAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"accountNumber"})
    public String getInstanceName(MetadataTools metadataTools) {
        return metadataTools.format(accountNumber);
    }
}