package fr.intellcap.artproject.controller;

import fr.intellcap.artproject.dto.ClientDTO;
import fr.intellcap.artproject.entities.Client;
import fr.intellcap.artproject.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("**")
@RestController
@RequestMapping("api/clients")
public class ClientController { 

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping("/")
    public List<Client> getAllClients(){
        return this.clientService.listClients();
    }
    @GetMapping("/dto")
    public List<ClientDTO> getAllClientsDTO(){
        return this.clientService.listClientsDTO();
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable Long id){
        return this.clientService.loadClientByClientId(id);
    }

   
    @PostMapping("/add-client")
    public ResponseEntity<Map<String, String>> addNewClient(@RequestBody ClientDTO clientDto) {
    Map<String, String> response = new HashMap<>();

    if (this.clientService.loadClientByEmail(clientDto.getEmail()) != null) {
        response.put("status", "error");
        response.put("message", "Cet email est déjà utilisé");
        return ResponseEntity.badRequest().body(response);
    }

    if (this.clientService.addNewClient(clientDto) != null) {
        response.put("status", "success");
        response.put("message", "Le client a été ajouté avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de l'ajout du client");
    return ResponseEntity.badRequest().body(response);
}


   
    @PostMapping("/update-client/{id}")
    
    public ResponseEntity<Map<String, String>> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDto) {
    Map<String, String> response = new HashMap<>();


    if(this.clientService.updateClient(id, clientDto) != null ) {
        response.put("status", "success");
        response.put("message", "Le client a été modifié avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de la modification du client");
    return ResponseEntity.badRequest().body(response);
}

    @DeleteMapping("/delete-client/{id}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable Long id){
        this.clientService.deleteClient(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Le client a été supprimé avec succès");
        return ResponseEntity.ok(response);

    }


}
