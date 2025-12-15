package com.example.donut.Repositories;

import com.example.donut.Controllers.DTO.ClientDTO;
import com.example.donut.Controllers.DTO.DonutDTO;
import com.example.donut.Repositories.Entities.Client;
import com.example.donut.Repositories.Entities.Donut;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DonutShopRepository {
    private static final String EMAIL_REGEX =  "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String MOBILE_REGEX = "^05[0-9]-?[0-9]{7}$";

    Map<String, Client> clientMap = new HashMap<>();
    Map<String, Donut> donutMap = new HashMap<>();

    public DonutShopRepository() {

        Client client = new Client("2154",Client.ContactMethods.EMAIL,"Hello@hello.com");
        Client client2 = new Client("2155",Client.ContactMethods.PHONE,"0546546584");
        Donut donut = new Donut("D001", "Chocolate", "Donut with chocolate glaze", 18.5f);
        donutMap.put(donut.getDonutId(),donut);
        client.saveDonut(donut);
        donut = new Donut("D002", "Jam", "Classic Jam donut", 5f);
        client.saveDonut(donut);
        donutMap.put(donut.getDonutId(),donut);
        donut = new Donut("D003", "Caramel", "Caramel filled donut", 20f);
        donutMap.put(donut.getDonutId(),donut);
        client2.saveDonut(donut);
        clientMap.put(client.getClientId(),client);
        clientMap.put(client2.getClientId(),client);
    }

    /**
     * If an ID exists in the system check contact method and contact value if they match whats saved, the client is authorized
     * If an ID does not exist, create a client if email or phone is valid and authorize the client
     * @param clientId
     * @param contactMethod
     * @param contactValue
     * @return
     */
    public boolean isAuthorized(String clientId,Client.ContactMethods contactMethod, String contactValue) {
        if (clientMap.containsKey(clientId)) {
            Client client = clientMap.get(clientId);
            return contactMethod == client.getContactMethod() && Objects.equals(contactValue, client.getContactMethodValue());
        }

        if ((contactMethod == Client.ContactMethods.EMAIL && contactValue.matches(EMAIL_REGEX)) || (contactMethod == Client.ContactMethods.PHONE && contactValue.matches(MOBILE_REGEX))) {
            Client client = new Client(clientId,contactMethod,contactValue);
            clientMap.put(clientId,client);
            return true;
        }
        return false;
    }

    public ClientDTO getClientDTO(String clientId) {
        if (!clientMap.containsKey(clientId)) {
            return null;
        }
        return new ClientDTO(clientMap.get(clientId));
    }

    public Client getClient(String clientId) {
        if (!clientMap.containsKey(clientId)) {
            return null;
        }
        return clientMap.get(clientId);
    }

    /**
     * If the donut ID exists and the client does not have it add it as a product of that client
     * @param clientId
     * @param donutId
     * @return
     */
    public ClientDTO addProductToClient(String clientId,String donutId) {
        if (donutMap.containsKey(donutId)) {
            Client client = clientMap.get(clientId);
            Donut donut = donutMap.get(donutId);
            if (!client.getDonutMap().containsKey(donut.getDonutId())) {
                client.saveDonut(donut);
                return new ClientDTO(client);
            }
            return  null;
        }
        return null;
    }

    /**
     * If the donut ID exists and the client has it then remove it as a product of that client
     * @param clientId
     * @param donutId
     * @return
     */
    public ClientDTO removeProductFromClient(String clientId,String donutId) {
        if (donutMap.containsKey(donutId)) {
            Client client = clientMap.get(clientId);
            Donut donut = donutMap.get(donutId);

            client.removeDonut(donut);
            return new ClientDTO(client);
        }
        return null;
    }

    /**
     * Add a donut to the shop, if the ID already exists, then update that donut with new values
     * @param dto
     * @return
     */
    public DonutDTO addOrUpdateDonut(DonutDTO dto) {
        Donut donut = new Donut(dto.getDonutId(), dto.getDonutName(), dto.getDonutDescription(), dto.getDonutPrice());
        donutMap.put(donut.getDonutId(),donut);
        return new DonutDTO(donut);
    }

    public List<DonutDTO> getProducts() {
        List<DonutDTO> products = new ArrayList<>();
        for (Map.Entry<String,Donut> entry : donutMap.entrySet()) {
            Donut donut = entry.getValue();
            products.add(new DonutDTO(donut));
        }
        return products;
    }

}
