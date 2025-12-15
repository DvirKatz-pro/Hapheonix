package com.example.donut.Controllers;

import com.example.donut.Controllers.DTO.ClientDTO;
import com.example.donut.Controllers.DTO.DonutDTO;
import com.example.donut.Controllers.DTO.DonutRequest;
import com.example.donut.Repositories.DonutShopRepository;
import com.example.donut.Repositories.Entities.Client;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donut")
public class DonutShopController {

    DonutShopRepository donutShopRepository;
    @Autowired
    public DonutShopController(DonutShopRepository donutShopRepository) {this.donutShopRepository = donutShopRepository;}

    /**
     * Get a client or create one
     * @param dto
     * @return
     */
    @GetMapping("/getClient")
    public ResponseEntity<ClientDTO> getClient(@Valid @RequestBody ClientDTO dto) {
        try {
            if (donutShopRepository.isAuthorized(dto.getClientId(),dto.getContactMethod(),dto.getContactMethodValue())) {
                ClientDTO result = donutShopRepository.getClientDTO(dto.getClientId());
                if (result != null) {
                    return ResponseEntity.ok(result);
                }
            }
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        dto.setReturnMessage("Error authorizing client, check contact method or contact value");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
    }

    /**
     * Have a client buy a donut with a request after check if authorized
     * @param dto
     * @return
     */
    @PostMapping("/request")
    public ResponseEntity<ClientDTO> buyDonut(@Valid @RequestBody DonutRequest dto) {
        try {
            ClientDTO clientDTO = dto.getClientDTO();
            if (donutShopRepository.isAuthorized(clientDTO.getClientId(),clientDTO.getContactMethod(),clientDTO.getContactMethodValue())) {
                ClientDTO result = donutShopRepository.addProductToClient(clientDTO.getClientId(), dto.getDonutId());
                if (result != null) {
                    return ResponseEntity.ok(result);
                }
                else {
                    clientDTO.setReturnMessage("Error adding product to client, check product exists");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clientDTO);
                }
            }
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        dto.getClientDTO().setReturnMessage("Error authorizing client, check contact method or contact value");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto.getClientDTO());
    }

    /**
     * Remove a donut from a client if the client has that donut after check if authorized
     * @param dto
     * @return
     */
    @DeleteMapping("/request")
    public ResponseEntity<ClientDTO> removeDonut(@Valid @RequestBody DonutRequest dto) {
        try {
            ClientDTO clientDTO = dto.getClientDTO();
            if (donutShopRepository.isAuthorized(clientDTO.getClientId(),clientDTO.getContactMethod(),clientDTO.getContactMethodValue())) {
                if (!donutShopRepository.getClient(clientDTO.getClientId()).getDonutMap().isEmpty()) {
                    ClientDTO result = donutShopRepository.removeProductFromClient(clientDTO.getClientId(), dto.getDonutId());
                    if (result != null) {
                        return ResponseEntity.ok(result);
                    }
                    else {
                        clientDTO.setReturnMessage("Error removing product from client, check product exists");
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(clientDTO);
                    }
                }
            }
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        dto.getClientDTO().setReturnMessage("Error authorizing client, check contact method or contact value");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto.getClientDTO());
    }

    /**
     * Add/update a donut to the store
     * @param dto
     * @return
     */
    @PostMapping("/product")
    public ResponseEntity<DonutDTO> addOrUpdateDonut(@Valid @RequestBody DonutDTO dto) {
        try {
            return ResponseEntity.ok(donutShopRepository.addOrUpdateDonut(dto));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    /**
     * Add/update a donut to the store
     * @return
     */
    @GetMapping("/product")
    public ResponseEntity<List<DonutDTO>> getProducts() {
        try {
            return ResponseEntity.ok(donutShopRepository.getProducts());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }


}
