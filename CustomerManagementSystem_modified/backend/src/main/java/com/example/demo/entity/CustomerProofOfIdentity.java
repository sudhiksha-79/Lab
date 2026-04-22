package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CUST_PRIDN")
@EntityListeners(AuditingEntityListener.class)
public class CustomerProofOfIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK to CustomerDetails
    @Column(name = "CSTID_IDFR")
    private Long customerIdentifier;

    @Column(name = "CSTPRID_VALUE")
    private String customerClassificationTypeValue;

    @Column(name = "CSTPRID_STRT_DT")
    private Date startDate;

    @Column(name = "CSTPRID_END_DT")
    private Date endDate;

    @Column(name = "CSTPRID_CRUD_VALUE")
    private Character crudValue;

    @Column(name = "CSTPRID_USER_ID")
    private String userID;

    @Column(name = "CSTPRID_WS_ID")
    private String workstationID;

    @Column(name = "CSTPRID_PRGM_ID")
    private String programID;

    // AUTO FILLED ON INSERT
    @CreatedDate
    @Column(name = "CSTPRID_HOST_TS", updatable = false)
    private Timestamp hostTimestamp;

    // AUTO FILLED ON UPDATE
    @LastModifiedDate
    @Column(name = "CSTPRID_LOCAL_TS")
    private Timestamp localTimestamp;

    @Column(name = "CSTPRID_ACPT_TS")
    private Timestamp acceptanceTimestamp;

    @Column(name = "CSTPRID_ACPT_TS_UTC_OFST")
    private Timestamp acceptanceTimestampUTCoffset;

    @Column(name = "CSTPRID_UUID")
    private String uuid;

    public CustomerProofOfIdentity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerIdentifier() { return customerIdentifier; }
    public void setCustomerIdentifier(Long customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public String getCustomerClassificationTypeValue() {
        return customerClassificationTypeValue;
    }

    public void setCustomerClassificationTypeValue(String customerClassificationTypeValue) {
        this.customerClassificationTypeValue = customerClassificationTypeValue;
    }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

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
