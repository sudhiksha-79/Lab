package com.example.demo.dto;

import java.sql.Date;

public class CustomerContactDetailsResponseDTO {

    private Long customerContactID;
    private Long customerId;
    private String customerContactDetails;
    private Date startDate;
    private Date endDate;
    private Character crudValue;
    private String userID;
    private String workstationID;
    private String programID;
    private String uuid;

    // Getters & Setters
    public Long getCustomerContactID() { return customerContactID; }
    public void setCustomerContactID(Long customerContactID) { this.customerContactID = customerContactID; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerContactDetails() { return customerContactDetails; }
    public void setCustomerContactDetails(String customerContactDetails) { this.customerContactDetails = customerContactDetails; }

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

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
