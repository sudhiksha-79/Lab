package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CUST_ADDR_CMPNT")
@EntityListeners(AuditingEntityListener.class)
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK to CustomerDetails
    @Column(name = "CSTID_IDFR")
    private Long customerIdentifier;

    @Column(name = "CSTADCMP_TYPE_ID")
    private String customerNameComponentType;

    @Column(name = "CSTADCMP_TYPE_VALUE")
    private String customerNameComponentValue;

    @Column(name = "CSTADCMP_EFCTV_DT")
    private Date effectiveDate;

    @Column(name = "CSTADCMP_CRUD_VALUE")
    private Character crudValue;

    @Column(name = "CSTADCMP_USER_ID")
    private String userID;

    @Column(name = "CSTADCMP_WS_ID")
    private String workstationID;

    @Column(name = "CSTADCMP_PRGM_ID")
    private String programID;

    // AUTO FILLED ON INSERT
    @CreatedDate
    @Column(name = "CSTADCMP_HOST_TS", updatable = false)
    private Timestamp hostTimestamp;

    // AUTO FILLED ON UPDATE
    @LastModifiedDate
    @Column(name = "CSTADCMP_LOCAL_TS")
    private Timestamp localTimestamp;

    @Column(name = "CSTADCMP_ACPT_TS")
    private Timestamp acceptanceTimestamp;

    @Column(name = "CSTADCMP_ACPT_TS_UTC_OFST")
    private Timestamp acceptanceTimestampUTCoffset;

    @Column(name = "CSTADCMP_UUID")
    private String uuid;

    public CustomerAddress() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerIdentifier() { return customerIdentifier; }
    public void setCustomerIdentifier(Long customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public String getCustomerNameComponentType() { return customerNameComponentType; }
    public void setCustomerNameComponentType(String customerNameComponentType) {
        this.customerNameComponentType = customerNameComponentType;
    }

    public String getCustomerNameComponentValue() { return customerNameComponentValue; }
    public void setCustomerNameComponentValue(String customerNameComponentValue) {
        this.customerNameComponentValue = customerNameComponentValue;
    }

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
    public void setAcceptanceTimestamp(Timestamp acceptanceTimestamp) {
        this.acceptanceTimestamp = acceptanceTimestamp;
    }

    public Timestamp getAcceptanceTimestampUTCoffset() { return acceptanceTimestampUTCoffset; }
    public void setAcceptanceTimestampUTCoffset(Timestamp acceptanceTimestampUTCoffset) {
        this.acceptanceTimestampUTCoffset = acceptanceTimestampUTCoffset;
    }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
