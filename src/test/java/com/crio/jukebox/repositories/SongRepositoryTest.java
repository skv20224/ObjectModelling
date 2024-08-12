package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SongRepositoryTest {
    
    private ISongRepository songRepository;

    @BeforeEach
    public void setup(){
        
        List<String> featuredArtist1 = List.of("allu","arjun","baggu");
        List<String> featuredArtist2 = List.of("atif","singh","rajjo");
        List<String> featuredArtist3 = List.of("alka","yagnik","pappu");
        // List<String> featuredArtist4 = List.of("allu","arjun","baggu");

        final Map<String, Song> map = new HashMap<String, Song>(){
            {
                put("1", new Song("1", "Jara Jara", "Sad", "favorite", "Arijit", featuredArtist1));
                put("2", new Song("2", "Zara sa", "Romance", "old", "KK", featuredArtist2));
                put("3", new Song("3", "Tere Liye", "Love", "Love", "KK", featuredArtist3));
                put("4", new Song("4", "Tum mile", "love", "nostalgia", "Atif", featuredArtist2));
            }
        };
        songRepository = new SongRepositoryImpl(map);
    }

    @Test
    @DisplayName("Test the save method")
    public void save(){
        Song song = new Song("blue blue", "water", "romance", "nawaz", List.of("allu","arjun","baggu"));
        Song expected = new Song("5", song);
        Song actual =  songRepository.save(song);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("find all should return alll the songs we have in repository")
    public void findAll(){
        

        List<Song> actualSongList = songRepository.findAll();

        Assertions.assertEquals(4, actualSongList.size());

    }

    @Test
    @DisplayName("test find by id method for song")
    public void findById(){
        String id = "1";
        // User expectedUser = 
        Optional<Song> actualSong = songRepository.findById(id);
        Assertions.assertEquals(id, actualSong.get().getId());
    }

    
    @Test
    @DisplayName("test find by id method for user id that doesn't exist")
    public void findByIdTestForInvalidId(){
        String id = "10";
        Optional<Song> expected = Optional.empty();

        Optional<Song> actual = songRepository.findById(id);
        // Assertions.assertThrows(UserNotFoundException, ()-> userRepository.findById(id));
        Assertions.assertEquals(expected,actual);
    }


}
