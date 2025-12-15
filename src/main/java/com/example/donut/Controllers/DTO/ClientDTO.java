package com.example.donut.Controllers.DTO;

import com.example.donut.Repositories.Entities.Client;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class ClientDTO {
    @NotBlank
    String clientId;
    @NotBlank
    Client.ContactMethods contactMethod;
    @NotBlank
    String contactMethodValue;

    List<DonutDTO> donutList;
    String returnMessage;

    public ClientDTO() {
        this.donutList = new ArrayList<>();
    }

    public ClientDTO(Client client) {
        this.clientId = client.getClientId();
        this.contactMethod = client.getContactMethod();
        this.contactMethodValue = client.getContactMethodValue();
        this.donutList = client.getDonutDTOList();
    }

    public ClientDTO(String clientId, Client.ContactMethods contactMethod, String contactMethodValue) {
        this.clientId = clientId;
        this.contactMethod = contactMethod;
        this.contactMethodValue = contactMethodValue;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Client.ContactMethods getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(Client.ContactMethods contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getContactMethodValue() {
        return contactMethodValue;
    }

    public void setContactMethodValue(String contactMethodValue) {
        this.contactMethodValue = contactMethodValue;
    }

    public List<DonutDTO> getDonutList() {
        return donutList;
    }

    public void setDonutList(List<DonutDTO> donutList) {
        this.donutList = donutList;
    }

    public String getReturnMessage() {return returnMessage;}

    public void setReturnMessage(String returnMessage) {this.returnMessage = returnMessage;}

}
