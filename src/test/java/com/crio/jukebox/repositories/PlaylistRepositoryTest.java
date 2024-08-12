package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlaylistRepositoryTest {
    
    IPlaylistRepository playlistRepository;

    @BeforeEach
    public void setup(){

        List<String> songList1 = List.of("1","2","3","4");
        List<String> songList2 = List.of("1","4","6","8");
        List<String> songList3 = List.of("5","2","7","9");

        final Map<String,Playlist> playlistMap = new HashMap<String,Playlist>(){
            {
                put("1", new Playlist("1", "1", "sad Song playlist", songList1));
                put("2", new Playlist("2", "2", "love Song playlist", songList2));
                put("3", new Playlist("3", "3", "romance Song playlist", songList3));
            }
        };
        
        playlistRepository = new PlaylistRepositoryImpl(playlistMap);
    }

    @Test
    @DisplayName("Test the save method")
    public void save(){

        Playlist entity = new Playlist("2", "avengers", List.of("2","4","6"));

        Playlist expected = new Playlist("4","2", "avengers", List.of("2","4","6"));
        Playlist actual = playlistRepository.save(entity);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Checking find all method")
    public void findAll(){

        List<Playlist> actual = playlistRepository.findAll();
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    @DisplayName("Checking find by id method with valid Id")
    public void findById(){
        String id = "3";
        Playlist expected = new Playlist("3", "3", "romance Song playlist", List.of("1","2","4"));
        Optional<Playlist> actual = playlistRepository.findById(id);
        Assertions.assertEquals(expected, actual.get());
    }


    @Test
    @DisplayName("Checking find by id method with Invalid Id")
    public void findById_shouldReturnEmptyPlaylist(){
        String id = "4";
        Optional<Playlist> expected = Optional.empty();
        Optional<Playlist> actual = playlistRepository.findById(id);
        Assertions.assertEquals(expected, actual);
    }




}
