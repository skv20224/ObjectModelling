package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends BaseEntity {
    private String userName;
    private List<Playlist> playlists;
    
    private Map<String,String> activePlaylist = new HashMap<>();
    
    public User(String id, User user){
        this(user.userName);
        this.id = id;
    }
    
    // public User(String id, User user){
    //     this(user.userName);
    //     this.id = id;
    //     // this.userName = name;
    // }

    public User(String name){
        this.userName = name;
        playlists = new ArrayList<>();
    }
    
    public User(String id, String name, List<Playlist> playlists){
        this.id = id;
        this.userName = name;
        this.playlists = playlists;
    }
    
    public Map<String,String> getActivePlaylist(){
        return this.activePlaylist;
    }

    public Boolean hasPlaylist(Playlist playlist){
        return this.playlists.contains(playlist);
    }
    
    public Map<String,String> setActivePlaylist(String playlistId, String songId ){
        this.activePlaylist.clear();
        this.activePlaylist.put(playlistId, songId);
        return this.activePlaylist;
    }



    public String getUserName() {
        return userName;
    }
    
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(Playlist playlist){
        this.playlists.add(playlist);
    }

    public void deletePlaylist(Playlist playlist){
        playlists.remove(playlist);
    }

    public void updatePlaylist(Integer idx, Playlist playlist){
        this.playlists.set(idx, playlist);
    }

    @Override
    public String toString() {
        return "User [id=" + id +  ", playlists=" + playlists + ", userName=" + userName + "]";
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
    
}
