package com.example.demo.dto;

import java.sql.Date;

public class CustomerIdentificationRequestDTO {

    private String customerIdentificationType;
    private String customerIdentificationItem;
    private Date effectiveDate;
    private Character crudValue;
    private String userID;
    private String workstationID;
    private String programID;
    private java.sql.Timestamp acceptanceTimestamp;
    private java.sql.Timestamp acceptanceTimestampUTCoffset;
    private String uuid;

    // Getters and Setters
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

    public java.sql.Timestamp getAcceptanceTimestamp() { return acceptanceTimestamp; }
    public void setAcceptanceTimestamp(java.sql.Timestamp acceptanceTimestamp) { this.acceptanceTimestamp = acceptanceTimestamp; }

    public java.sql.Timestamp getAcceptanceTimestampUTCoffset() { return acceptanceTimestampUTCoffset; }
    public void setAcceptanceTimestampUTCoffset(java.sql.Timestamp acceptanceTimestampUTCoffset) { this.acceptanceTimestampUTCoffset = acceptanceTimestampUTCoffset; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
