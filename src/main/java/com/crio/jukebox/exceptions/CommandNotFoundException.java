package com.crio.jukebox.exceptions;

public class CommandNotFoundException extends RuntimeException{
    public CommandNotFoundException(String msg){
        super(msg);
    }
}
