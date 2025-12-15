package com.example.donut.Repositories.Entities;

import com.example.donut.Controllers.DTO.DonutDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Client {

    String clientId;

    ContactMethods contactMethod;
    String contactMethodValue;
    HashMap<String,Donut> donutMap = new HashMap<>();

    public enum ContactMethods {
        EMAIL,
        PHONE,
    }

    public Client(String clientId,ContactMethods contactMethod, String contactMethodValue) {
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

    public ContactMethods getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(ContactMethods contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getContactMethodValue() {
        return contactMethodValue;
    }

    public void setContactMethodValue(String contactMethodValue) {
        this.contactMethodValue = contactMethodValue;
    }

    public Donut saveDonut(Donut donut) {
        if (donutMap.containsKey(donut.getDonutId())) {
            return null;
        }
        donutMap.put(donut.getDonutId(),donut);
        return donut;
    }

    public Donut removeDonut(Donut donut) {
        return donutMap.remove(donut.getDonutId());
    }

    public List<DonutDTO> getDonutDTOList() {
        List<DonutDTO> donutDTOS = new ArrayList<>();
        for(Donut donut : this.donutMap.values()) {
            DonutDTO donutDTO = new DonutDTO(donut.getDonutId(),donut.getDonutName(),donut.getDonutDescription(),donut.getDonutPrice());
            donutDTOS.add(donutDTO);
        }
        return donutDTOS;
    }

    public HashMap<String,Donut> getDonutMap() {
        return donutMap;
    }
}
