package fr.intellcap.artproject.services;



import fr.intellcap.artproject.dto.ClientDTO;
import fr.intellcap.artproject.entities.Client;

import java.util.List;

public interface ClientService {

    Client addNewClient(ClientDTO clientDto);
    Client updateClient(Long id, ClientDTO clientDTO);
    Client updateData(Client client, ClientDTO clientDto);
    void deleteClient(Long id);
    List<ClientDTO> listClientsDTO();
    List<Client> listClients();
    Client loadClientByEmail(String email);
    Client loadClientById(Long id);
    ClientDTO loadClientByClientId(Long id);


}
