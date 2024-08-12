package com.crio.jukebox.services;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.Album;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.ISongRepository;

public class SongService implements  ISongService {

    final private ISongRepository songRepository;
    
    public SongService(ISongRepository songRepository){
        this.songRepository = songRepository;
    }

    @Override
    public Song createSong(String songId ,String SONG_NAME,String GENRE, String ALBUM_NAME, String ALBUM_ARTIST, List<String> featuredArtists) {
        // TODO Auto-generated method stub
        Song song = new Song(songId, SONG_NAME, GENRE, ALBUM_NAME, ALBUM_ARTIST, featuredArtists);
        return songRepository.save(song);
    }

    @Override
    public void deleteSong(Song entity) {
        // TODO Auto-generated method stub
        songRepository.delete(entity);
    }


    @Override
    public void playSong(Song song) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Optional<Song> findSong(String id) {
        // TODO Auto-generated method stub
        return songRepository.findById(id);
    }

    @Override
    public List<Song> findAllSong() {
        // TODO Auto-generated method stub
        return songRepository.findAll();
    }
    
}
