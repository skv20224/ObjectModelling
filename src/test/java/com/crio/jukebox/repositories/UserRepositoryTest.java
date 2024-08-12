package com.crio.jukebox.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {
    private IUserRepository userRepository;

    List<Playlist> playlist1;
    List<Playlist> playlist2;
    List<Playlist> playlist3;


    @BeforeEach
    public void setup(){
    
    List<String> songList1 = List.of("1","2","3","4");
    playlist1 = new ArrayList<>();
    playlist1.add(new Playlist("1", "1", "shivamplaylist", songList1));

    List<String> songList2 = List.of("1","4");
    playlist2 = new ArrayList<>();
    playlist2.add(new Playlist("2", "2", "atulplaylist", songList2));

    List<String> songList3 = List.of("5","6");
    playlist3 = new ArrayList<>();
    playlist3.add(new Playlist("3", "3", "atulplaylist", songList2));
    playlist3.add(new Playlist("3", "3", "atulplaylist", songList3));



    Map<String, User> map = new HashMap<>();
        
    map.put("1", new User("1", "shivam", playlist1));
    map.put("2", new User("2", "atul", playlist2));
    map.put("3", new User("3", "krishna", playlist3));
    
    userRepository = new UserRepositoryImpl(map);
    }

    @Test
    @DisplayName("save method should create and return new user")
    public void saveUser(){
        
        User user = new User("Gourav");
        
        User actual = userRepository.save(user);

        Assertions.assertEquals(user,actual);
    }

    @Test
    @DisplayName("find all should return alll the user we have in repository")
    public void findAllUser(){
        
        List<User> expectedUserList = new ArrayList<User>(){
            {
                add(new User("1", "shivam", playlist1));
                add(new User("2", "atul", playlist2));
                add(new User("3", "krishna", playlist3));
            }
        };

        List<User> actualUserList = userRepository.findAll();

        Assertions.assertEquals(expectedUserList, actualUserList);

    }

    // @Test
    // @DisplayName("find all method should return blank list if repository is blank")
    // public void findAll_shouldReturnEmptyList(){
        

    //     List<User> actualUserList = userRepository.findAll();

    //     Assertions.assertEquals(0, actualUserList.size());

    // }

    @Test
    @DisplayName("test find by id method for user")
    public void findById(){
        String id = "1";
        // User expectedUser = 
        Optional<User> actualUser = userRepository.findById(id);
        Assertions.assertEquals(id, actualUser.get().getId());
    }

    
    @Test
    @DisplayName("test find by id method for user id that doesn't exist")
    public void findByIdTestForInvalidId(){
        String id = "10";
        Optional<User> expected = Optional.empty();

        Optional<User> actual = userRepository.findById(id);
        // Assertions.assertThrows(UserNotFoundException, ()-> userRepository.findById(id));
        Assertions.assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Delete user by user id if user exist")
    public void deleteById(){

        String id = "30";
        userRepository.deleteById(id);
        Assertions.assertEquals(2, userRepository.count());

    }
}
