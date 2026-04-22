package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CUST_NME_CMPNT")
@EntityListeners(AuditingEntityListener.class)
public class CustomerName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CSTID_IDFR")
    private Long customerIdentifier;

    @Column(name = "CSTNMCMP_TYPE_ID")
    private String customerNameComponentType;

    @Column(name = "CSTNMCMP_TYPE_VALUE")
    private String customerNameComponentValue;

    @Column(name = "CSTNMCMP_EFCTV_DT")
    private Date effectiveDate;

    @Column(name = "CSTNMCMP_CRUD_VALUE")
    private Character crudValue;

    @Column(name = "CSTNMCMP_USER_ID")
    private String userID;

    @Column(name = "CSTNMCMP_WS_ID")
    private String workstationID;

    @Column(name = "CSTNMCMP_PRGM_ID")
    private String programID;

    @CreatedDate
    @Column(name = "CSTNMCMP_HOST_TS", updatable = false)
    private Timestamp hostTimestamp;

    @LastModifiedDate
    @Column(name = "CSTNMCMP_LOCAL_TS")
    private Timestamp localTimestamp;

    @Column(name = "CSTNMCMP_ACPT_TS")
    private Timestamp acceptanceTimestamp;

    @Column(name = "CSTNMCMP_ACPT_TS_UTC_OFST")
    private Timestamp acceptanceTimestampUTCoffset;

    @Column(name = "CSTNMCMP_UUID")
    private String uuid;

    public CustomerName() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerIdentifier() { return customerIdentifier; }
    public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

    public String getCustomerNameComponentType() { return customerNameComponentType; }
    public void setCustomerNameComponentType(String customerNameComponentType) { this.customerNameComponentType = customerNameComponentType; }

    public String getCustomerNameComponentValue() { return customerNameComponentValue; }
    public void setCustomerNameComponentValue(String customerNameComponentValue) { this.customerNameComponentValue = customerNameComponentValue; }

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
