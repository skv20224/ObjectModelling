package com.crio.jukebox.services;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.Song;

public interface ISongService {
    
    public Song createSong(String songId,String SONG_NAME,String GENRE, String ALBUM_NAME, String ALBUM_ARTIST, List<String> featuredArtists);
    public void deleteSong(Song entity);
    public void playSong(Song song);
    public Optional<Song> findSong(String id);
    public List<Song> findAllSong();
}
