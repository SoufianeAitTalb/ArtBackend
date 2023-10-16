package fr.intellcap.artproject.services.impl;

import fr.intellcap.artproject.dto.ClientDTO;
import fr.intellcap.artproject.entities.Command;
import fr.intellcap.artproject.repositories.ClientRepo;
import fr.intellcap.artproject.repositories.CommandRepo;
import fr.intellcap.artproject.services.ClientService;
import javax.persistence.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.intellcap.artproject.entities.Client;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientServiceImp implements ClientService {

    private final ClientRepo clientRepo;
    private final CommandRepo commandRepo;


    @Autowired
    public ClientServiceImp(ClientRepo clientRepo,CommandRepo commandRepo){
        this.clientRepo = clientRepo;
        this.commandRepo=commandRepo;

    }

    @Transactional
    @Override
    public Client addNewClient(ClientDTO clientDto) {
        Client client = new Client();

        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());
        client.setAge(clientDto.getAge());
        client.setAddress(clientDto.getAddress());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setCin(clientDto.getCin());


        return this.clientRepo.save(client);
    }

    @Transactional 
    @Override
    public Client updateClient(Long id, ClientDTO clientDto) {
        Client client = this.clientRepo.findById(id).orElse(null);

        if(client != null){

            client = this.updateData(client, clientDto);

            return this.clientRepo.save(client);
        }
        return null;
    }

    @Override
    public Client updateData(Client client, ClientDTO clientDto) {
        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());
        client.setAge(clientDto.getAge());
        client.setAddress(clientDto.getAddress());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setCin(clientDto.getCin());

        return client;
    }

    @Override
    public void deleteClient(Long id) {

//        clientRepo.deleteById(id);
        Client client = clientRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));



        List<Command> commands = client.getCommands();
        for (Command command : commands) {
            commandRepo.deleteById(command.getCommandId());

        }

        clientRepo.delete(client);
    }

    @Override
    public List<Client> listClients() {
        return this.clientRepo.findAll();
    }

    @Override
    public List<ClientDTO> listClientsDTO() {
        List<Client> clients = this.clientRepo.findAll();
        List<ClientDTO> clientsDTO = clients.stream()
                .map(this::convertToClientDTO)
                .collect(Collectors.toList());
        return clientsDTO; // Return the list of ClientDTOs
    }

    public ClientDTO convertToClientDTO(Client client) {
         if(client == null){
            return null;
        }
        ClientDTO clientDto = new ClientDTO();
         clientDto.setClientId(client.getUserId());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setAge(client.getAge());
        clientDto.setAddress(client.getAddress());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setCin(client.getCin());
        return clientDto;
    }

    @Override
    public Client loadClientById(Long id) {
        return this.clientRepo
                .findById(id)
                .orElse(null);
    }

    @Override
    public ClientDTO loadClientByClientId(Long id) {
        Client client = this.clientRepo.findById(id).orElse(null);
        if(client == null){
            return null;
        }
        ClientDTO clientDto = new ClientDTO();


        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setAge(client.getAge());
        clientDto.setAddress(client.getAddress());
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setCin(client.getCin());

        return clientDto;
    }


    @Override
    public Client loadClientByEmail(String email) {
        return this.clientRepo
                .findByEmail(email)
                .orElse(null);
    }









}
