package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepositoryImpl implements ISongRepository {

    private final Map<String, Song> songMap;
    private Integer autoIncrement;

    public SongRepositoryImpl(){
        this.songMap = new HashMap<>();
        this.autoIncrement = 0;
    }

    
    public SongRepositoryImpl(Map<String,Song> map){
        this.songMap = map;
        this.autoIncrement = map.size();
    }

    @Override
    public Song save(Song entity) {
        // TODO Auto-generated method stub
        if(entity.getId()==null){
            autoIncrement++;
            Song song = new Song(String.valueOf(autoIncrement), entity);
            songMap.put(String.valueOf(autoIncrement), song);
            return song;
        }
        songMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return songMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return songMap.containsKey(id);
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        songMap.remove(entity.getId());
        }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        songMap.remove(id);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return songMap.size();
    }
    
}
