package com.crio.jukebox.exceptions;

public class SongNotFoundException extends RuntimeException{
    public SongNotFoundException(String msg){
        super(msg);
    }
}
