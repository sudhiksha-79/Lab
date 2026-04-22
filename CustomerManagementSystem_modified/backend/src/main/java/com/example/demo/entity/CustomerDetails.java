package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CUST_DTL")
@EntityListeners(AuditingEntityListener.class)
public class CustomerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CST_IDFR")
    private Long id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeqGen")
    @SequenceGenerator(
            name = "customerSeqGen",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    @Column(name = "CSTID_IDFR")
    private Long customerIdentifier;

    @Column(name = "CUST_TYPE")
    private String customerType;

    @Column(name = "CUST_DOB")
    private Date customerDOB;

    @Column(name = "CUST_STATUS")
    private String customerStatus;

    @Column(name = "CUST_GENDR")
    private String customerGender;

    @Column(name = "CUST_PRFRD_LNG")
    private String customerPreferredLanguage;

    @Column(name = "CUST_CNTRY_ORGTN")
    private String customerCountryOrigin;

    @Column(name = "CUST_EFCTV_DT")
    private Date effectiveDate;

    @Column(name = "CUST_CRUD_VALUE")
    private Character crudValue;

    @Column(name = "CUST_USER_ID")
    private String userID;

    @Column(name = "CUST_WS_ID")
    private String workstationID;

    @Column(name = "CUST_PRGM_ID")
    private String programID;

    // AUTO FILLED ON INSERT
    @CreatedDate
    @Column(name = "CUST_HOST_TS", updatable = false)
    private Timestamp hostTimestamp;

    // AUTO FILLED ON UPDATE
    @LastModifiedDate
    @Column(name = "CUST_LOCAL_TS")
    private Timestamp localTimestamp;

    @Column(name = "CUST_ACPT_TS")
    private Timestamp acceptanceTimestamp;

    @Column(name = "CUST_ACPT_TS_UTC_OFST")
    private Timestamp acceptanceTimestampUTCoffset;

    @Column(name = "CUST_UUID")
    private String uuid;

    public CustomerDetails() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerIdentifier() { return customerIdentifier; }
    public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }

    public Date getCustomerDOB() { return customerDOB; }
    public void setCustomerDOB(Date customerDOB) { this.customerDOB = customerDOB; }

    public String getCustomerStatus() { return customerStatus; }
    public void setCustomerStatus(String customerStatus) { this.customerStatus = customerStatus; }

    public String getCustomerGender() { return customerGender; }
    public void setCustomerGender(String customerGender) { this.customerGender = customerGender; }

    public String getCustomerPreferredLanguage() { return customerPreferredLanguage; }
    public void setCustomerPreferredLanguage(String customerPreferredLanguage) { this.customerPreferredLanguage = customerPreferredLanguage; }

    public String getCustomerCountryOrigin() { return customerCountryOrigin; }
    public void setCustomerCountryOrigin(String customerCountryOrigin) { this.customerCountryOrigin = customerCountryOrigin; }

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
