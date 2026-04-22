package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CUST_CL")
@EntityListeners(AuditingEntityListener.class)
public class CustomerClassification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTCL_ID")
    private Long id;

    @Column(name = "CSTCL_TYP")
    private String customerClassificationType;

    @Column(name = "CSTCL_TYP_VALUE")
    private String customerClassificationTypeValue;

    @Column(name = "CSTCL_EFCTV_DT")
    private Date effectiveDate;

    @Column(name = "CSTCL_CRUD_VALUE")
    private Character crudValue;

    @Column(name = "CSTCL_USER_ID")
    private String userID;

    @Column(name = "CSTCL_WS_ID")
    private String workstationID;

    @Column(name = "CSTCL_PRGM_ID")
    private String programID;

    @CreatedDate
    @Column(name = "CSTCL_HOST_TS", updatable = false)
    private Timestamp hostTimestamp;

    @LastModifiedDate
    @Column(name = "CSTCL_LOCAL_TS")
    private Timestamp localTimestamp;

    @Column(name = "CSTCL_ACPT_TS")
    private Timestamp acceptanceTimestamp;

    @Column(name = "CSTCL_ACPT_TS_UTC_OFST")
    private Timestamp acceptanceTimestampUTCoffset;

    @Column(name = "CSTCL_UUID")
    private String uuid;

    public CustomerClassification() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerClassificationType() { return customerClassificationType; }
    public void setCustomerClassificationType(String customerClassificationType) { this.customerClassificationType = customerClassificationType; }

    public String getCustomerClassificationTypeValue() { return customerClassificationTypeValue; }
    public void setCustomerClassificationTypeValue(String customerClassificationTypeValue) { this.customerClassificationTypeValue = customerClassificationTypeValue; }

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
