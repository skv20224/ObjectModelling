package com.crio.jukebox.services;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;

public interface IPlaylistService {
    public Playlist createPlaylist(String userId, String playlistName, List<String> songIdList);
    public void deletePlaylist(Playlist playlist);
    public void deletePlaylist(String playlistId);
    public List<Playlist> findAllPlaylist();
    public Optional<Playlist> findPlaylist(String id);
    public void playPlaylist(String userId, String playlistId);
    public Playlist modifyPlaylist(String operation, String userId, String playlistId, List<String> songList);
    public void playSong(String userId, String operation);
}
