package com.crio.jukebox.exceptions;

public class PlaylistEmptyException extends RuntimeException{
    public PlaylistEmptyException(String msg){
        super(msg);
    }
}
