package com.example.demo.dto;

import java.sql.Date;

public class CustomerProofOfIdentityRequestDTO {

    private Long customerIdentifier;
    private String customerClassificationTypeValue;
    private Date startDate;
    private Date endDate;
    private Character crudValue;
    private String userID;
    private String workstationID;
    private String programID;
    private java.sql.Timestamp acceptanceTimestamp;
    private java.sql.Timestamp acceptanceTimestampUTCoffset;
    private String uuid;

    // --- Getters & Setters ---
    public Long getCustomerIdentifier() { return customerIdentifier; }
    public void setCustomerIdentifier(Long customerIdentifier) { this.customerIdentifier = customerIdentifier; }

    public String getCustomerClassificationTypeValue() { return customerClassificationTypeValue; }
    public void setCustomerClassificationTypeValue(String customerClassificationTypeValue) { this.customerClassificationTypeValue = customerClassificationTypeValue; }

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

    public java.sql.Timestamp getAcceptanceTimestamp() { return acceptanceTimestamp; }
    public void setAcceptanceTimestamp(java.sql.Timestamp acceptanceTimestamp) { this.acceptanceTimestamp = acceptanceTimestamp; }

    public java.sql.Timestamp getAcceptanceTimestampUTCoffset() { return acceptanceTimestampUTCoffset; }
    public void setAcceptanceTimestampUTCoffset(java.sql.Timestamp acceptanceTimestampUTCoffset) { this.acceptanceTimestampUTCoffset = acceptanceTimestampUTCoffset; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
