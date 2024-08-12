package com.crio.jukebox.services;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepository;
import com.crio.jukebox.repositories.PlaylistRepositoryImpl;
import com.crio.jukebox.repositories.UserRepositoryImpl;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IPlaylistRepository playlistRepository;

    public UserService(IUserRepository userRepository , IPlaylistRepository playlistRepository){
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }

    @Override
    public User createUser(String userName) {
        // TODO Auto-generated method stub
        User user = new User(userName);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) throws UserNotFoundException{
        // TODO Auto-generated method stub
            String id = user.getId();
            userRepository.findById(id).orElseThrow( ()-> new UserNotFoundException("User with this id"+ id + "doesn't exist"));
            List<Playlist> pList = user.getPlaylists();
            userRepository.deleteById(id);
            for(Playlist playlist : pList){
                playlistRepository.delete(playlist);
            }
          
    }

    @Override
    public Optional<User> findUser(User user) {
        // TODO Auto-generated method stub
        return userRepository.findById(user.getId());
    }

    @Override
    public Optional<User> findUserById(String userId) {
        // TODO Auto-generated method stub
        return userRepository.findById(userId);
    }
    
}
