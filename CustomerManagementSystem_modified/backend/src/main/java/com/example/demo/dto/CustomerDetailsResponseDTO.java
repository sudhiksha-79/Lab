package com.example.demo.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CustomerDetailsResponseDTO {

    private Long customerIdentifier;
    private String customerType;
    private Date customerDOB;
    private String customerStatus;
    private String customerGender;
    private String customerPreferredLanguage;
    private String customerCountryOrigin;
    private Date effectiveDate;
    private Character crudValue;       // audit field
    private String userID;
    private String workstationID;
    private String programID;
    private Timestamp hostTimestamp;   // auto-set on CREATE
    private Timestamp localTimestamp;  // auto-set on CREATE/UPDATE
    private String uuid;

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
    public void setHostTimestamp(Timestamp hostTimestamp) { this.hostTimestamp = hostTimestamp; }

    public Timestamp getLocalTimestamp() { return localTimestamp; }
    public void setLocalTimestamp(Timestamp localTimestamp) { this.localTimestamp = localTimestamp; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
