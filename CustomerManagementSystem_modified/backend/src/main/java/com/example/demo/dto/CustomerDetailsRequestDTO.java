package com.example.demo.dto;

import java.sql.Date;
import java.sql.Timestamp;

// Request DTO for creating/updating a customer
public class CustomerDetailsRequestDTO {

    private String customerType;
    private Date customerDOB;
    private String customerStatus;
    private String customerGender;
    private String customerPreferredLanguage;
    private String customerCountryOrigin;
    private Date effectiveDate;
    private String userID;
    private String workstationID;
    private String programID;
    private Timestamp acceptanceTimestamp;
    private Timestamp acceptanceTimestampUTCoffset;
    private String uuid;

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

    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }

    public String getWorkstationID() { return workstationID; }
    public void setWorkstationID(String workstationID) { this.workstationID = workstationID; }

    public String getProgramID() { return programID; }
    public void setProgramID(String programID) { this.programID = programID; }

    public Timestamp getAcceptanceTimestamp() { return acceptanceTimestamp; }
    public void setAcceptanceTimestamp(Timestamp acceptanceTimestamp) { this.acceptanceTimestamp = acceptanceTimestamp; }

    public Timestamp getAcceptanceTimestampUTCoffset() { return acceptanceTimestampUTCoffset; }
    public void setAcceptanceTimestampUTCoffset(Timestamp acceptanceTimestampUTCoffset) { this.acceptanceTimestampUTCoffset = acceptanceTimestampUTCoffset; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
