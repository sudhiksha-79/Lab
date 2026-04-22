package com.example.demo.dto;

import java.sql.Date;

public class CustomerNameResponseDTO {

    private Long id;
    private Long customerIdentifier;
    private String customerNameComponentType;
    private String customerNameComponentValue;
    private Date effectiveDate;
    private Character crudValue;
    private String userID;
    private String workstationID;
    private String programID;
    private String uuid;

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

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
