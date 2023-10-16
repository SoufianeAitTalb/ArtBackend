package fr.intellcap.artproject.services.impl;

import fr.intellcap.artproject.dto.CommandDTO;

import fr.intellcap.artproject.entities.Client;
import fr.intellcap.artproject.entities.Command;
import fr.intellcap.artproject.entities.Paint;
import fr.intellcap.artproject.repositories.CommandRepo;
import fr.intellcap.artproject.repositories.PaintRepo;
import fr.intellcap.artproject.services.ClientService;
import fr.intellcap.artproject.services.CommandService;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandServiceImp implements CommandService {

    private final CommandRepo commandRepo;
    private final ClientService clientService;
    private final PaintRepo paintRepo;



    @Autowired
    public CommandServiceImp(CommandRepo commandRepo,ClientService clientService,PaintRepo paintRepo){
        this.commandRepo = commandRepo;
        this.clientService=clientService;
        this.paintRepo=paintRepo;

    }
    @Override
    public Command addNewCommand(CommandDTO commandDTO) {
        Command command=new Command();
        command.setCommandId(commandDTO.getCommadId());
        Client client = clientService.loadClientById(commandDTO.getIdClient());
        Optional<Paint> paint = paintRepo.findById(commandDTO.getIdPaint());
        command.setClient(client);
        command.setPaint(paint.get());
        return commandRepo.save(command);
    }

    @Override
    public Command updateCommand(Long id, CommandDTO commandDTO) {
        Command command = this.commandRepo.findById(id).orElse(null);

        if(command != null){

            command = this.updateData(command, commandDTO);

            return this.commandRepo.save(command);
        }
        return null;
    }

    @Override
    public Command updateData(Command command, CommandDTO commandDTO) {

        command.setCommandId(commandDTO.getCommadId());
        Client client = clientService.loadClientById(commandDTO.getIdClient());
        Optional<Paint> paint = paintRepo.findById(commandDTO.getIdPaint());
        command.setClient(client);
        command.setPaint(paint.get());
        return command;
    }

    @Override
    public void deleteCommand(Long id) {

        Command command = commandRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("recipient not found"));


        commandRepo.delete(command);

    }

    @Override
    public List<CommandDTO> listCommandsDTO() {
        return null;
    }

    @Override
    public List<Command> listCommands() {
        return commandRepo.findAll();
    }


    @Override
    public Command loadCommandById(Long id) {
        return this.commandRepo
                .findById(id)
                .orElse(null);
    }
    @Override
    public CommandDTO loadCommandByCommandId(Long id) {
        Command command = this.commandRepo.findById(id).orElse(null);
        if(command == null){
            return null;
        }
        CommandDTO commandDTO=new CommandDTO();
        commandDTO.setCommadId(command.getCommandId());
        commandDTO.setIdClient(command.getClient().getUserId());
        commandDTO.setIdPaint(command.getPaint().getPaintId());
        return commandDTO;
    }
}
