package fr.intellcap.artproject.controller;

import fr.intellcap.artproject.dto.CommandDTO;

import fr.intellcap.artproject.entities.Command;

import fr.intellcap.artproject.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("**")
@RestController
@RequestMapping("api/commands")
public class CommandController {

    private final CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService){
        this.commandService = commandService;
    }

    @GetMapping("/")
    public List<Command> getAllCommands(){
        return this.commandService.listCommands();
    }
    @GetMapping("/dto")
    public List<CommandDTO> getAllCommandsDTO(){
        return this.commandService.listCommandsDTO();
    }

    @GetMapping("/{id}")
    public CommandDTO getCommandById(@PathVariable Long id){
        return this.commandService.loadCommandByCommandId(id);
    }

   
    @PostMapping("/add-command")
    public ResponseEntity<Map<String, String>> addNewCommand(@RequestBody CommandDTO commandDto) {
    Map<String, String> response = new HashMap<>();


    if (this.commandService.addNewCommand(commandDto) != null) {
        response.put("status", "success");
        response.put("message", "La commande a été ajouté avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de l'ajout de commande");
    return ResponseEntity.badRequest().body(response);
}


   
    @PostMapping("/update-command/{id}")
    
    public ResponseEntity<Map<String, String>> updateCommand(@PathVariable Long id, @RequestBody CommandDTO commandDto) {
    Map<String, String> response = new HashMap<>();


    if(this.commandService.updateCommand(id, commandDto) != null ) {
        response.put("status", "success");
        response.put("message", "La commande a été modifié avec succès");
        return ResponseEntity.ok(response);
    }

    response.put("status", "error");
    response.put("message", "Une erreur s'est produite lors de la modification de commande");
    return ResponseEntity.badRequest().body(response);
}

    @DeleteMapping("/delete-command/{id}")
    public ResponseEntity<Map<String, String>> deleteCommand(@PathVariable Long id){
        this.commandService.deleteCommand(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "La commande a été supprimé avec succès");
        return ResponseEntity.ok(response);

    }


}
