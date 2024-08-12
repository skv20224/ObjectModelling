package com.crio.jukebox.services;

import java.util.Optional;
import com.crio.jukebox.entities.User;

public interface IUserService {
    public User createUser(String userName);
    public void deleteUser(User user);
    public Optional<User> findUser(User user);
    public Optional<User> findUserById(String userId);
    
}
