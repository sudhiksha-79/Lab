package com.example.demo.entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "CUST_CNTCT_DTLS")
@EntityListeners(AuditingEntityListener.class)
public class CustomerContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTCNDT_ID")
    private Long customerContactID;

    @OneToOne
    @JoinColumn(name = "CSTID_IDFR", nullable = false)
    private CustomerDetails customerIdentification;

    @Column(name = "CSTCNDT_VALUE")
    private String customerContactDetails;

    @Column(name = "CSTCNDT_STRT_DT")
    private Date startDate;

    @Column(name = "CSTCNDT_END_DT")
    private Date endDate;

    @Column(name = "CSTCNDT_CRUD_VALUE")
    private Character crudValue;

    @Column(name = "CSTCNDT_USER_ID")
    private String userID;

    @Column(name = "CSTCNDT_WS_ID")
    private String workstationID;

    @Column(name = "CSTCNDT_PRGM_ID")
    private String programID;

    @CreatedDate
    @Column(name = "CSTCNDT_HOST_TS", updatable = false)
    private Timestamp hostTimestamp;

    @LastModifiedDate
    @Column(name = "CSTCNDT_LOCAL_TS")
    private Timestamp localTimestamp;

    @Column(name = "CSTCNDT_ACPT_TS")
    private Timestamp acceptanceTimestamp;

    @Column(name = "CSTCNDT_ACPT_TS_UTC_OFST")
    private Timestamp acceptanceTimestampUTCoffset;

    @Column(name = "CSTCNDT_UUID")
    private String uuid;

    public CustomerContactDetails() {}

    // --- Getters & Setters ---
    public Long getCustomerContactID() { return customerContactID; }
    public void setCustomerContactID(Long customerContactID) { this.customerContactID = customerContactID; }

    public CustomerDetails getCustomerIdentification() { return customerIdentification; }
    public void setCustomerIdentification(CustomerDetails customerIdentification) {
        this.customerIdentification = customerIdentification;
    }

    public String getCustomerContactDetails() { return customerContactDetails; }
    public void setCustomerContactDetails(String customerContactDetails) {
        this.customerContactDetails = customerContactDetails;
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
    public void setUuid(String uuid) { this.uuid = uuid; } // ✅ Fixed setter
}
