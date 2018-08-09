package com.enes.ws.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.enes.ws.model.Greeting;
import com.enes.ws.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GreetingServiceBean implements GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    public Collection<Greeting> findAll() {
        Collection<Greeting> greetings = greetingRepository.findAll();
        
        return greetings;
    }

    public Greeting findOne(Long id) {
        Greeting greeting = greetingRepository.findOne(id);
        return greeting;
    }

    public Greeting create(Greeting greeting) {
        
        if(greeting.getId() != null){
            //Cannot create greeting with specified ID value 
        }
        Greeting savedGreeting = greetingRepository.save(greeting);
        return savedGreeting;
    }

    public Greeting update(Greeting greeting) {
        Greeting greetingPersisted = findOne(greeting.getId());
        if(greetingPersisted == null){
            
            //Cannot update greeting that hasnt been persisted
            return null;
        }
        
        Greeting updatedGreeting = greetingRepository.save(greeting);
        return updatedGreeting;
    }

    public void delete(Long id) {
        greetingRepository.delete(id);

    }

}
