package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.crio.codingame.repositories.IUserRepository;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTest {
    
    
    // @Mock
    // IUserRepository userRepositoryMock;
    @Mock
    IUserService userServiceMock;
    @Mock
    ISongService SongServiceMock;
    @Mock
    IPlaylistRepository playlistRepositoryMock;
    
    @InjectMocks
    PlaylistService playlistServiceMock;
    
    // @Test
    // @DisplayName("Testing the create playlist method")
    // public void createPlaylistTest(){

    //     Song song = new Song("abc", "pop", "album-1", "arijit", List.of("KK","PK"));
    //     User user = new User("shivam");
    //     when(userServiceMock.findUserById(anyString())).thenReturn(Optional.of(user));
    //     when(SongServiceMock.findSong(anyString())).thenReturn(Optional.of(song));
        
    //     Playlist actual = playlistServiceMock.createPlaylist("1", "shivamPlaylist", List.of("1","2","3"));
    //     Playlist expected = new Playlist( "1", "1", "shivamPlaylist",List.of("1","2","3"));
 
    //     Assertions.assertEquals(expected, actual);
    // }


    @Test
    @DisplayName("Testing the Modify Playlist method with add song operation")
    public void playlistModifyTestWithAddSongOperation(){

        List<String> songlist1 = new ArrayList<String>(){
            {
                add("10");
                add("30");
            }
        };
        
        List<String> songlist2 = new ArrayList<String>(){
            {
                add("2");
                add("4");
            }
        };
        
        final Playlist playlist = new Playlist("1", "1", "Sad song", songlist1);
        final Song song = new Song("1", "abc", "xyz", "album", "artist", List.of("abc","xyz"));

        final List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(new Playlist("1", "1", "Sad song", songlist1));
                add(new Playlist("2", "1", "Happy song", songlist2));
            }
        };

        User user = new User("1", "shivam", playlists);
        when(userServiceMock.findUserById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.of(playlist));
        when(SongServiceMock.findSong(anyString())).thenReturn(Optional.of(song));
        
        playlistServiceMock.modifyPlaylist("ADD-SONG", "1", "1", List.of("1","2","3","4"));
        // System.out.println(user.getPlaylists().get(0));
        Playlist expected = new Playlist("1", "1", "Sad song", List.of("10","30","1","2","3","4"));
        Assertions.assertEquals(expected.getSongList(), user.getPlaylists().get(0).getSongList() );
        Assertions.assertEquals(expected, user.getPlaylists().get(0));

        verify(userServiceMock,times(1)).findUserById(anyString());
        verify(playlistRepositoryMock,times(1)).findById(anyString());
        verify(SongServiceMock,times(4)).findSong(anyString());
    }
    
    @Test
    @DisplayName("Testing the Modify Playlist method with remove song operation")
    public void playlistModifyTestWithDeleteSongOperation(){

        List<String> songlist1 = new ArrayList<String>(){
            {
                add("10");
                add("30");
            }
        };
        
        List<String> songlist2 = new ArrayList<String>(){
            {
                add("2");
                add("4");
            }
        };
        
        final Playlist playlist = new Playlist("1", "1", "Sad song", songlist1);
        final Song song = new Song("1", "abc", "xyz", "album", "artist", List.of("abc","xyz"));

        final List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(new Playlist("1", "1", "Sad song", songlist1));
                add(new Playlist("2", "1", "Happy song", songlist2));
            }
        };

        User user = new User("1", "shivam", playlists);
        when(userServiceMock.findUserById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.of(playlist));
        when(SongServiceMock.findSong(anyString())).thenReturn(Optional.of(song));
        
        playlistServiceMock.modifyPlaylist("DELETE-SONG", "1", "1", List.of("10","30"));
        // System.out.println(user.getPlaylists().get(0));
        // Playlist expected = new Playlist("1", "1", "Sad song", List.of("10","30","1","2","3","4"));
        Playlist expected = new Playlist("1", "1", "Sad song", List.of());
        // System.out.println(user.getPlaylists().get(0));
        Assertions.assertEquals(expected.getSongList(), user.getPlaylists().get(0).getSongList());
        Assertions.assertEquals(expected, user.getPlaylists().get(0));
        
        
        verify(userServiceMock,times(1)).findUserById(anyString());
        verify(playlistRepositoryMock,times(1)).findById(anyString());
        //    if you wanted to delete all songs from the playlist please keep it commented else uncomment
        // verify(SongServiceMock,times(expected.songlistSize())).findSong(anyString());
    }
    
    @Test
    @DisplayName("Testing the Modify Playlist method with invalid user id")
    public void playlistModifyTest_sholdThrowUserNotFoundException(){

        List<String> songlist1 = new ArrayList<String>(){
            {
                add("10");
                add("30");
            }
        };
        
        List<String> songlist2 = new ArrayList<String>(){
            {
                add("2");
                add("4");
            }
        };
        
        final Playlist playlist = new Playlist("1", "1", "Sad song", songlist1);
        final Song song = new Song("1", "abc", "xyz", "album", "artist", List.of("abc","xyz"));

        final List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(new Playlist("1", "1", "Sad song", songlist1));
                add(new Playlist("2", "1", "Happy song", songlist2));
            }
        };

        User user = new User("1", "shivam", playlists);
        when(userServiceMock.findUserById( eq("2"))).thenThrow(new UserNotFoundException("user not found with this id"));
        // when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.of(playlist));
        // when(SongServiceMock.findSong(anyString())).thenReturn(Optional.of(song));
        
        // playlistServiceMock.modifyPlaylist("DELETE-SONG", "2", "1", List.of("10","30"));
        // System.out.println(user.getPlaylists().get(0));
        // Playlist expected = new Playlist("1", "1", "Sad song", List.of("10","30","1","2","3","4"));
        // Playlist expected = new Playlist("1", "1", "Sad song", List.of());
        // System.out.println(user.getPlaylists().get(0));
        Assertions.assertThrows(UserNotFoundException.class,()-> playlistServiceMock.modifyPlaylist("DELETE-SONG", "2", "1", List.of("10","30")));
        // Assertions.assertEquals(expected.getSongList(), user.getPlaylists().get(0).getSongList());
        // Assertions.assertEquals(expected, user.getPlaylists().get(0));
        
        
        verify(userServiceMock,times(1)).findUserById(eq("2"));
        // verify(playlistRepositoryMock,times(1)).findById(anyString());
        //    if you wanted to delete all songs from the playlist please keep it commented else uncomment
        // verify(SongServiceMock,times(expected.songlistSize())).findSong(anyString());
    }

    @Test
    @DisplayName("Testing the play playlist method")
    public void testPlayPlaylistMethod(){

        List<String> songlist1 = new ArrayList<String>(){
            {
                add("10");
                add("30");
            }
        };
        
        List<String> songlist2 = new ArrayList<String>(){
            {
                add("2");
                add("4");
            }
        };
        
        final Playlist playlist = new Playlist("1", "1", "Sad song", songlist1);
        final Song song = new Song("1", "abc", "xyz", "album", "artist", List.of("abc","xyz"));

        final List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(new Playlist("1", "1", "Sad song", songlist1));
                add(new Playlist("2", "1", "Happy song", songlist2));
            }
        };

        User user = new User("1", "shivam", playlists);
        
        when(userServiceMock.findUserById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.of(playlist));
        when(SongServiceMock.findSong(anyString())).thenReturn(Optional.of(song));

        playlistServiceMock.playPlaylist("1", "1");

        verify(userServiceMock,times(1)).findUserById(anyString());
        verify(playlistRepositoryMock,times(1)).findById(anyString());
        verify(SongServiceMock,times(1)).findSong(anyString());
        

    }
    
    @Test
    @DisplayName("Testing the play song method with next operation")
    public void testPlaySongMethodWithNextOperation(){

        List<String> songlist1 = new ArrayList<String>(){
            {
                add("10");
                add("30");
            }
        };
        
        List<String> songlist2 = new ArrayList<String>(){
            {
                add("2");
                add("4");
            }
        };
        
        final Playlist playlist = new Playlist("1", "1", "Sad song", songlist1);
        final Song song = new Song("1", "abc", "xyz", "album", "artist", List.of("abc","xyz"));

        final List<Playlist> playlists = new ArrayList<Playlist>(){
            {
                add(new Playlist("1", "1", "Sad song", songlist1));
                add(new Playlist("2", "1", "Happy song", songlist2));
            }
        };

        User user = new User("1", "shivam", playlists);
        user.setActivePlaylist("1", "10");
        when(userServiceMock.findUserById(anyString())).thenReturn(Optional.of(user));
        when(playlistRepositoryMock.findById(anyString())).thenReturn(Optional.of(playlist));
        when(SongServiceMock.findSong(anyString())).thenReturn(Optional.of(song));

        // playlistServiceMock.playPlaylist("1", "1");
        playlistServiceMock.playSong("1", "NEXT");

        verify(userServiceMock,times(1)).findUserById(anyString());
        verify(playlistRepositoryMock,times(1)).findById(anyString());
        verify(SongServiceMock,times(1)).findSong(anyString());


    }

}
