package com.company.samplepetroleumaccountingproject.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.datatype.DatatypeFormatter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "PETROLEUM_OPERATION", indexes = {
        @Index(name = "IDX_PETROLEUM_OPERATION_SUPPLIER_CLIENT", columnList = "SUPPLIER_CLIENT_ID"),
        @Index(name = "IDX_PETROLEUM_OPERATION_OPERATING_ACCOUNT", columnList = "OPERATING_ACCOUNT_ID"),
        @Index(name = "IDX_PETROLEUM_OPERATION_SENDER_RECEIVER", columnList = "SENDER_RECEIVER_ID"),
        @Index(name = "IDX_PETROLEUM_OPERATION_VEHICLE", columnList = "VEHICLE_ID")
}, uniqueConstraints = {
        @UniqueConstraint(name = "IDX_PETROLEUM_OPERATION_UNQ", columnNames = {"DATE_"})
})
@Entity
public class PetroleumOperation {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotNull
    @Column(name = "DATE_", nullable = false)
    private LocalDateTime date;

    @NotNull
    @Column(name = "OPERATION_TYPE", nullable = false)
    private String operationType;

    @JoinColumn(name = "SUPPLIER_CLIENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Partner supplierClient;

    @JoinColumn(name = "OPERATING_ACCOUNT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount operatingAccount;

    @JoinColumn(name = "SENDER_RECEIVER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Partner senderReceiver;

    @JoinColumn(name = "VEHICLE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    @Column(name = "VEHICLE_DETAILS")
    @Lob
    private String vehicleDetails;

    @NotNull(message = "{msg://com.company.samplepetroleumaccountingproject.entity/PetroleumOperation.operationDetails.validation.NotNull}")
    @Composition
    @OneToMany(mappedBy = "petroleumOperation")
    private List<PetroleumOperationDetail> operationDetails;

    public List<PetroleumOperationDetail> getOperationDetails() {
        return operationDetails;
    }

    public void setOperationDetails(List<PetroleumOperationDetail> operationDetails) {
        this.operationDetails = operationDetails;
    }

    public String getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Partner getSenderReceiver() {
        return senderReceiver;
    }

    public void setSenderReceiver(Partner senderReceiver) {
        this.senderReceiver = senderReceiver;
    }

    public BankAccount getOperatingAccount() {
        return operatingAccount;
    }

    public void setOperatingAccount(BankAccount operatingAccount) {
        this.operatingAccount = operatingAccount;
    }

    public Partner getSupplierClient() {
        return supplierClient;
    }

    public void setSupplierClient(Partner supplierClient) {
        this.supplierClient = supplierClient;
    }

    public PetroleumOperationType getOperationType() {
        return operationType == null ? null : PetroleumOperationType.fromId(operationType);
    }

    public void setOperationType(PetroleumOperationType operationType) {
        this.operationType = operationType == null ? null : operationType.getId();
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

    @InstanceName
    @DependsOnProperties({"date", "operationType"})
    public String getInstanceName(MetadataTools metadataTools, DatatypeFormatter datatypeFormatter) {
        return String.format("%s %s",
                datatypeFormatter.formatLocalDateTime(date),
                metadataTools.format(getOperationType()));
    }
}