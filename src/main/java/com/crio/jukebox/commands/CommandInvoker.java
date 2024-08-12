package com.crio.jukebox.commands;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.exceptions.CommandNotFoundException;

public class CommandInvoker {
    
    private static final Map<String, ICommand> commandMap = new HashMap<>();
    
    public void register(String commandName, ICommand command){
        commandMap.put(commandName, command);
    }

    private ICommand getCommand(String commandName){
        return commandMap.get(commandName);
    }

    public void executeCommad( String commandName, List<String> tokens ) throws FileNotFoundException{
        ICommand command = getCommand(commandName);
        if(command==null){
            throw new CommandNotFoundException("No such command found");
        }
        command.execute(tokens);
    }
    
}
