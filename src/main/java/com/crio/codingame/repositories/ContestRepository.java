package com.crio.codingame.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
// import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;

public class ContestRepository implements IContestRepository {

    private final Map<String,Contest> contestMap;
    private Integer autoIncrement = 0;

    
    
    public ContestRepository() {
        contestMap = new HashMap<String,Contest>();
    }

    public ContestRepository(Map<String, Contest> contestMap) {
        this.contestMap = contestMap;
        this.autoIncrement = contestMap.size();
    }

    @Override
    public Contest save(Contest entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Contest c = new Contest(Integer.toString(autoIncrement),entity.getName(),entity.getQuestions(),entity.getLevel(),entity.getCreator(),entity.getContestStatus());
            contestMap.put(c.getId(),c);
            return c;
        }
        contestMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Contest Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<Contest> findAll() {
       return contestMap.values().stream().collect(Collectors.toList());
    //  return Collections.emptyList();
    }

    @Override
    public Optional<Contest> findById(String id) {
        // if(contestMap.containsKey(id))
        //     return Optional.of(contestMap.get(id));
        // return Optional.empty();
        return Optional.ofNullable(contestMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return contestMap.containsKey(id);
        // return false;
        // return false;
    }

    @Override
    public void delete(Contest entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        contestMap.remove(id);
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return contestMap.size();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Contest Present in the Repository provided Level
    // Tip:- Use Java Streams

    @Override
    public List<Contest> findAllContestLevelWise(Level level) {
        List<Contest> contestList = new ArrayList<>();
        for(String key : contestMap.keySet()){
            Contest contest = contestMap.get(key);
            if(contest.getLevel().name().equals(level.name()))
                contestList.add(contest);
            // if(contest.getLevel().getClass().getName().equals(level.name()))
        }
        return contestList;
    }

    
}
