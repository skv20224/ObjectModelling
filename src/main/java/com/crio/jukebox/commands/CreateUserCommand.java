package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    public CreateUserCommand(IUserService userService){
        this.userService = userService;
    }


    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userName = tokens.get(1);
        User createdUser = userService.createUser(userName);
        System.out.println(createdUser.getId() + " " + createdUser.getUserName());
    }
    
}
