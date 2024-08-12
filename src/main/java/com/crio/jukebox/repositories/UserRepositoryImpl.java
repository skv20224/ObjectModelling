package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.User;

public class UserRepositoryImpl implements IUserRepository {

    private final Map<String, User> userMap;
    private Integer autoIncrement;

    public UserRepositoryImpl(){
        this.userMap = new HashMap<>();
        this.autoIncrement = 0;
    }

    public UserRepositoryImpl(Map<String,User> map){
        this.userMap = map;
        this.autoIncrement = map.size();
    }

    @Override
    public User save(User entity) {
        // TODO Auto-generated method stub
        if(entity.getId()==null){
            autoIncrement++;
            User user = new User( String.valueOf(autoIncrement), entity);
            userMap.put(String.valueOf(autoIncrement), user);
            return user;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return userMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(userMap.get(id));
        // return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return userMap.containsKey(id);
        // return false;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        deleteById(entity.getId());
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        userMap.remove(id);
        return ;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return userMap.size();
    }
    
}
