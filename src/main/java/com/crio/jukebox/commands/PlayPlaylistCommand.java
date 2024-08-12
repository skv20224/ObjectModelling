package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);
        try {
            playlistService.playPlaylist(userId, playlistId);
        } catch (RuntimeException e) {
            //TODO: handle exception
            e.getMessage();
        }
    }
    
}
