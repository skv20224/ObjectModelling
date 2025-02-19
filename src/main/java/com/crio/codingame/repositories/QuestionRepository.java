package com.crio.codingame.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crio.codingame.entities.Level;
import com.crio.codingame.entities.Question;

public class QuestionRepository implements IQuestionRepository {

    private final Map<String,Question> questionMap;
    private Integer autoIncrement = 0;

    public QuestionRepository(){
        questionMap = new HashMap<String,Question>();
    }

    public QuestionRepository(Map<String, Question> questionMap) {
        this.questionMap = questionMap;
        this.autoIncrement = questionMap.size();
    }

    @Override
    public Question save(Question entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            Question q = new Question(Integer.toString(autoIncrement),entity.getTitle(),entity.getLevel(),entity.getScore());
            questionMap.put(q.getId(),q);
            return q;
        }
        questionMap.put(entity.getId(),entity);
        return entity;
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Question Present in the Repository
    // Tip:- Use Java Streams

    @Override
    public List<Question> findAll() {
        List<Question> questionsList = new ArrayList<>();
        for(String key : questionMap.keySet()){
            questionsList.add(questionMap.get(key));
        }
        return questionsList;
    }

    @Override
    public Optional<Question> findById(String id) {
        return Optional.ofNullable(questionMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        // if(questionMap.get(id)==null)
        //  return false;
        // return true;    

        return questionMap.containsKey(id);
    }

    @Override
    public void delete(Question entity) {
        String id = entity.getId();
        deleteById(id);
        // if( questionMap.containsKey(entity.getId()) )
        //     questionMap.remove(entity.getId());
        // TODO Auto-generated method stub
        // return ;
    }

    @Override
    public void deleteById(String id) {
        if(questionMap.containsKey(id))
            questionMap.remove(id);
        return ;
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return questionMap.size();
    }

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Find all the list of Question Present in the Repository provided Level
    // Tip:- Use Java Streams

    @Override
    public List<Question> findAllQuestionLevelWise(Level level) {

        List<Question> qList = new ArrayList<>();
        for(String key : questionMap.keySet()){
            if(questionMap.get(key).getLevel().name().equals(level.name()) ){
                qList.add(questionMap.get(key));
            }
        }
     return qList;
    }
    
}
