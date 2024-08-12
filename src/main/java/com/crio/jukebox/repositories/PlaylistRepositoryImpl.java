package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Playlist;

public class PlaylistRepositoryImpl implements IPlaylistRepository {

    private final Map<String,Playlist> playlistMap;
    private  Integer autoIncrement = 0;


    public PlaylistRepositoryImpl(){
        this.playlistMap = new HashMap<>();
        // this.autoIncrement = 0;
    }
    
    public PlaylistRepositoryImpl(Map<String,Playlist> playlistMap){
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
    }

    @Override
    public Playlist save(Playlist entity) {
        // TODO Auto-generated method stub
        if(entity.getId()==null){
            autoIncrement++;
            Playlist playlist = new Playlist(String.valueOf(autoIncrement), entity.getUserId(), entity.getPlaylistName(), entity.getSongList());
            playlistMap.put(playlist.getId(), playlist);
            return playlist;
        }
            playlistMap.put(entity.getId(), entity);
            return entity;
    }

    @Override
    public List<Playlist> findAll() {
        // TODO Auto-generated method stub
        return playlistMap.values().stream().collect(Collectors.toList());
        // return null;
    }

    @Override
    public Optional<Playlist> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return playlistMap.containsKey(id);
    }

    @Override
    public void delete(Playlist entity) {
        // TODO Auto-generated method stub
        playlistMap.remove(entity.getId());
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        playlistMap.remove(id);
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return playlistMap.size();
    }
    
}
