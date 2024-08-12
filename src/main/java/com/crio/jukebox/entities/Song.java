package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity {
    private String songName;
    private String genre;
    private String album;
    private String artist;
    private List<String> featuredArtist;
    
    public Song(String id, Song song) {
        this(song.songName,song.genre,song.album,song.artist,song.featuredArtist);
        this.id = id;
    }

    
    public Song(String songName, String genre, String album, String artist,
            List<String> featuredArtist) {
        this.songName = songName;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.featuredArtist = featuredArtist;
    }

  
    public Song(String id, String songName, String genre, String album, String artist,
            List<String> featuredArtist) {
        
        this.id = id;
        this.songName = songName;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.featuredArtist = featuredArtist;
    }

  

    public String getArtist() {
        return this.artist;
    }

    public List<String> getFeaturedArtist() {
        return this.featuredArtist;
    }

    public String getSongName() {
        return this.songName;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getAlbum() {
        return this.album;
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
        Song other = (Song) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Song [id=" + id +  ", album=" + album + ", artist=" + artist + ", featuredArtist=" + featuredArtist
                + ", genre=" + genre + ", songName=" + songName + "]";
    }


   
    
}
