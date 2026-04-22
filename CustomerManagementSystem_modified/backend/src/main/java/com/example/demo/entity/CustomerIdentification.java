package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CUST_IDFN")
@EntityListeners(AuditingEntityListener.class)
public class CustomerIdentification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTIN_IDFR")
    private Long customerIdentificationID;

    @Column(name = "CSTIN_ID_TYPE")
    private String customerIdentificationType;

    @Column(name = "CSTIN_ID_ITEM")
    private String customerIdentificationItem;

    @Column(name = "CSTIN_EFCTV_DT")
    private Date effectiveDate;

    @Column(name = "CSTIN_CRUD_VALUE")
    private Character crudValue;

    @Column(name = "CSTIN_USER_ID")
    private String userID;

    @Column(name = "CSTIN_WS_ID")
    private String workstationID;

    @Column(name = "CSTIN_PRGM_ID")
    private String programID;

    @CreatedDate
    @Column(name = "CSTIN_HOST_TS", updatable = false)
    private Timestamp hostTimestamp;

    @LastModifiedDate
    @Column(name = "CSTIN_LOCAL_TS")
    private Timestamp localTimestamp;

    @Column(name = "CSTIN_ACPT_TS")
    private Timestamp acceptanceTimestamp;

    @Column(name = "CSTIN_ACPT_TS_UTC_OFST")
    private Timestamp acceptanceTimestampUTCoffset;

    @Column(name = "CSTIN_UUID")
    private String uuid;

    public CustomerIdentification() {}

    // --- Getters and Setters ---
    public Long getCustomerIdentificationID() { return customerIdentificationID; }
    public void setCustomerIdentificationID(Long customerIdentificationID) { this.customerIdentificationID = customerIdentificationID; }

    public String getCustomerIdentificationType() { return customerIdentificationType; }
    public void setCustomerIdentificationType(String customerIdentificationType) { this.customerIdentificationType = customerIdentificationType; }

    public String getCustomerIdentificationItem() { return customerIdentificationItem; }
    public void setCustomerIdentificationItem(String customerIdentificationItem) { this.customerIdentificationItem = customerIdentificationItem; }

    public Date getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(Date effectiveDate) { this.effectiveDate = effectiveDate; }

    public Character getCrudValue() { return crudValue; }
    public void setCrudValue(Character crudValue) { this.crudValue = crudValue; }

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getWorkstationID() { return workstationID; }
    public void setWorkstationID(String workstationID) { this.workstationID = workstationID; }

    public String getProgramID() { return programID; }
    public void setProgramID(String programID) { this.programID = programID; }

    public Timestamp getHostTimestamp() { return hostTimestamp; }

    public Timestamp getLocalTimestamp() { return localTimestamp; }

    public Timestamp getAcceptanceTimestamp() { return acceptanceTimestamp; }
    public void setAcceptanceTimestamp(Timestamp acceptanceTimestamp) { this.acceptanceTimestamp = acceptanceTimestamp; }

    public Timestamp getAcceptanceTimestampUTCoffset() { return acceptanceTimestampUTCoffset; }
    public void setAcceptanceTimestampUTCoffset(Timestamp acceptanceTimestampUTCoffset) { this.acceptanceTimestampUTCoffset = acceptanceTimestampUTCoffset; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
