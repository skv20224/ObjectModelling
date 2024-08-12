package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class PlaySongCommand implements ICommand{

    private final IPlaylistService playlistService;

    

    public PlaySongCommand(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId = tokens.get(1);
        String operation = tokens.get(2);
        try {
            playlistService.playSong(userId, operation);
        } catch (RuntimeException e) {
            //TODO: handle exception
            e.getMessage();
        }
    }
    
}
