package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand {

    private final IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        // TODO Auto-generated method stub
        String operation = tokens.get(1);
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        List<String> songIdList = tokens.subList(4, tokens.size());
        try {  
            Playlist modifiedPlaylist = playlistService.modifyPlaylist(operation, userId, playlistId, songIdList);
            // System.out.println("Playlist ID check by shivam,-");
            System.out.println("Playlist ID - "+modifiedPlaylist.getId());
            System.out.println("Playlist Name - "+modifiedPlaylist.getPlaylistName());
            System.out.println("Song IDs - "+ modifiedPlaylist.getSongList());

        } catch (RuntimeException e) {
            //TODO: handle exception
            e.getMessage();
        }
        
    }
    
}
