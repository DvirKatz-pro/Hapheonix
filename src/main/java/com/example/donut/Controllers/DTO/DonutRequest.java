package com.example.donut.Controllers.DTO;

import jakarta.validation.constraints.NotBlank;

public class DonutRequest {
    @NotBlank
    ClientDTO clientDTO;
    @NotBlank
    String donutId;

    public DonutRequest(ClientDTO clientDTO, String donutId) {
        this.clientDTO = clientDTO;
        this.donutId = donutId;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public String getDonutId() {
        return donutId;
    }

    public void setDonutId(String donutId) {
        this.donutId = donutId;
    }
}
