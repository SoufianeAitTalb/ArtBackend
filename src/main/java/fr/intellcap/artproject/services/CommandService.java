package fr.intellcap.artproject.services;


import fr.intellcap.artproject.dto.CommandDTO;
import fr.intellcap.artproject.entities.Command;

import java.util.List;

public interface CommandService {

    Command addNewCommand(CommandDTO clientDto);
    Command updateCommand(Long id, CommandDTO commandDTO);
    Command updateData(Command command, CommandDTO commandDto);
    void deleteCommand(Long id);
    List<CommandDTO> listCommandsDTO();
    List<Command> listCommands();

    Command loadCommandById(Long id);
    CommandDTO loadCommandByCommandId(Long id);


}
