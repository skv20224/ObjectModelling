package com.crio.jukebox.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.crio.codingame.repositories.IUserRepository;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlaylistEmptyException;
import com.crio.jukebox.exceptions.PlaylistNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundException;
import com.crio.jukebox.exceptions.SongNotFoundInPlaylistException;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;

public class PlaylistService implements IPlaylistService {

    private final IPlaylistRepository playlistRepository;
    // final private IUserRepository userRepository;
    private final IUserService userService;
    private final ISongService songService;

    public PlaylistService(IPlaylistRepository playlistRepository, com.crio.jukebox.repositories.IUserRepository userRepository, IUserService userService, ISongService songService ){
        this.playlistRepository = playlistRepository;
        // this.userRepository = userRepository;
        this.userService = userService;
        this.songService = songService;
    }

    @Override
    public Playlist createPlaylist(String userId, String playlistName, List<String> songIdList) throws UserNotFoundException,SongNotFoundException {
        // TODO Auto-generated method stub
        User user = userService.findUserById(userId).orElseThrow(()-> new UserNotFoundException("User with this id"+userId+"not exist"));
        
        if(songIdList==null || songIdList.size()==0){
            
            Playlist entity =  new Playlist(userId, playlistName, songIdList);
            // System.out.println(entity);
            
            entity = playlistRepository.save(entity);
            // System.out.println(entity);
            user.addPlaylist(entity);
            // System.out.println(user);
            return entity;
        }
        
        for(String id : songIdList){
            songService.findSong(id).orElseThrow(()-> new SongNotFoundException("Some Requested Songs Not Available. Please try again."));
        }
        
        Playlist entity = playlistRepository.save(new Playlist(userId, playlistName, songIdList));
        user.addPlaylist(entity);
        // System.out.println(entity);
        // System.out.println(user);
        return entity;
        
    }

    @Override
    public void deletePlaylist(Playlist playlist) {
        // TODO Auto-generated method stub
        
        playlistRepository.delete(playlist);
        
    }

    @Override
    public void deletePlaylist(String playlistId) throws PlaylistNotFoundException {
        // TODO Auto-generated method stub
            Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(()-> new PlaylistNotFoundException("Playlist Not Found"));
            playlistRepository.deleteById(playlistId);
            userService.findUserById(playlist.getUserId()).get().deletePlaylist(playlist);
    }

    @Override
    public List<Playlist> findAllPlaylist() {
        // TODO Auto-generated method stub
        return playlistRepository.findAll();
    }

    @Override
    public Optional<Playlist> findPlaylist(String id) {
        // TODO Auto-generated method stub
        return playlistRepository.findById(id);
    }

    @Override
    public void playPlaylist(String userId, String PlaylistId) {
        // TODO Auto-generated method stub
        User user = userService.findUserById(userId).orElseThrow(()-> new UserNotFoundException("User with this id " +userId+ "doesn't exist"));
        Playlist playlist = playlistRepository.findById(PlaylistId).orElseThrow(()-> new PlaylistNotFoundException("Playlist with this id" + PlaylistId + "doesn't exist"));
        
        if(playlist.getSongList()==null || playlist.getSongList().size()==0){
            throw new PlaylistEmptyException("Playlist is empty.");
        }

        String songId = playlist.getSongList().get(0);
        Song song = songService.findSong(songId).orElseThrow(()-> new SongNotFoundException("Song with this id" + songId+"doesn't exist"));

        user.setActivePlaylist(PlaylistId, songId);
        System.out.println("Current Song Playing");
        System.out.println("Song - " + song.getSongName());
        System.out.println("Album - " + song.getAlbum());
        System.out.println("Artists - " + song.getFeaturedArtist());
        // System.out.println("Current Song Playing");
    }
    
    @Override
    public void playSong(String userId, String operation){
        User user = userService.findUserById(userId).orElseThrow(()-> new UserNotFoundException("User with this id " +userId+ "doesn't exist"));
        Map<String,String> activePlaylistMap =  user.getActivePlaylist();
        String activePlaylistId = new String(); 
        for(String key : activePlaylistMap.keySet())
            activePlaylistId = key;
        
        Integer activePlaylistSonglistSize = playlistRepository.findById(activePlaylistId).get().songlistSize();
            
        String activeSongId = activePlaylistMap.get(activePlaylistId);
        
        Playlist activePlaylist = playlistRepository.findById(activePlaylistId).get();
        
        List<String> songlistsForActivePlaylist = activePlaylist.getSongList();
        int indexOfActiveSongId = IntStream.range(0,activePlaylistSonglistSize).filter(idx -> songlistsForActivePlaylist.get(idx).equals(activeSongId)).findFirst().getAsInt();

        String updatedSongId = "0";
        
        if(operation.equals("NEXT")){
            if((activePlaylistSonglistSize - indexOfActiveSongId) == 1){
                indexOfActiveSongId = 0;
                updatedSongId = activePlaylist.getSongList().get(indexOfActiveSongId);
            }
            else{
                indexOfActiveSongId++;
                updatedSongId = activePlaylist.getSongList().get(indexOfActiveSongId);
            }
        }
        
        else if(operation.equals("BACK")){
            if(indexOfActiveSongId==0){
                indexOfActiveSongId = activePlaylistSonglistSize-1;
                updatedSongId = activePlaylist.getSongList().get(indexOfActiveSongId);
            }
            
            else if(indexOfActiveSongId>0){
                indexOfActiveSongId -= 1;
                updatedSongId = activePlaylist.getSongList().get(indexOfActiveSongId);
            }
        }

        
        else{
            // Playlist activePlaylist = playlistRepository.findById(activePlaylistId).get();
            if(!activePlaylist.songExist(operation))
                throw new SongNotFoundInPlaylistException("Song Not Found in the current active playlist.");
            updatedSongId = operation;
        }

        user.setActivePlaylist(activePlaylistId, updatedSongId);
        Song song = songService.findSong(String.valueOf(updatedSongId)).get();
        // Song song = songService.findSong(operation).orElseThrow(()-> new SongNotFoundException("Song with this id" +operation+"doesn't exist"));

        // user.setActivePlaylist(PlaylistId, songId);
        System.out.println("Current Song Playing");
        System.out.println("Song - " + song.getSongName());
        System.out.println("Album - " + song.getAlbum());
        System.out.println("Artists - " + song.getFeaturedArtist());
    }


    @Override
    public Playlist modifyPlaylist(String operation, String userId, String playlistId, List<String> songlist) throws UserNotFoundException,
        SongNotFoundException, PlaylistNotFoundException,SongNotFoundInPlaylistException{
        // TODO Auto-generated method stub
        
        User user = userService.findUserById(userId).orElseThrow(()-> new UserNotFoundException("User with this id doesn't exist"));
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(()-> new PlaylistNotFoundException("Playlist with this id doesn't exist"));
        
        for(String sid : songlist){
            songService.findSong(sid).orElseThrow(()-> new SongNotFoundException("Some Requested Songs Not Available. Please try again."));
        }
        
        

        if(operation.equals("ADD-SONG")){
            for(String sid : songlist){
                if(!playlist.songExist(sid)){
                    System.out.println(playlist);
                    playlist.addSong(sid);
                    playlist.addSong("78");
                }
                }

            // System.out.println(playlist);
            }
            
        else if(operation.equals("DELETE-SONG")){
            for(String sid : songlist){
                if(playlist.songExist(sid))
                    playlist.deleteSong(sid);
                else{
                    throw new SongNotFoundInPlaylistException("Some Requested Songs for Deletion are not present in the playlist. Please try again.");
                }
            }
        }
        
        List<Playlist> playlists = user.getPlaylists();
        int index = IntStream.range(0, playlists.size()).filter(idx -> playlists.get(idx).getId().equals(playlistId)).findFirst().getAsInt();

        // String id = user.getPlaylists().stream().filter((e)-> e.getId().equals(playlistId)).collect(Collectors.toList()).get(0).getId();
        user.updatePlaylist(index, playlist);
        System.out.println(user);
        return playlistRepository.save(playlist);
        // return playlist;
        // user.
    }
    
}
