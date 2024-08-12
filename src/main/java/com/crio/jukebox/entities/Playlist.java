package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;


public class Playlist extends BaseEntity{
    private String playlistName;
    private List<String> songList = new ArrayList<>();
    private String userId;

    public Playlist(String userId, String playlistName, List<String> songList){
        // this()
        this.userId = userId;
        this.playlistName = playlistName;
        this.songList = songList;
    }

    public Playlist(String id, String userId, String playlistName, List<String> songList){
        this.id = id;
        this.userId = userId;
        this.playlistName = playlistName;
        this.songList = songList;
    }

    // public Playlist(String userId, String playlistName){
    //     this.userId = userId;
    //     this.playlistName = playlistName;
    //     this.songList = new ArrayList<>();
    // }




    public String getPlaylistName() {
        return playlistName;
    }
    
    public List<String> getSongList() {
        return songList;
    }

    public void addSong(String songId){
        this.songList.add(songId);
        // return;
    }

    public boolean deleteSong(String songId){
        if(this.songList.contains(songId))
            return songList.remove(songId);
        return false;
    }

    public String getUserId() {
        return userId;
    }

    public boolean songExist(String songId){
        return this.songList.contains(songId);
    }

    public Integer songlistSize(){
        return this.songList.size();
    }

    @Override
    public String toString() {
        return "Playlist [id = " + id +", playlistName=" + playlistName + ", songList=" + songList + ", user="
                + userId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Playlist other = (Playlist) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
