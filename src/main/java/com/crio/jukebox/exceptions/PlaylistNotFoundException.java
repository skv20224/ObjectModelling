package com.crio.jukebox.exceptions;

public class PlaylistNotFoundException extends RuntimeException{
    public PlaylistNotFoundException(String msg){
        super(msg);
    }
}
