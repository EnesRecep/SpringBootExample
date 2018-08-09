package com.enes.ws.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.enes.ws.model.Greeting;
import com.enes.ws.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class GreetingServiceBean implements GreetingService {

    @Autowired
    private GreetingRepository greetingRepository;

    public Collection<Greeting> findAll() {
        Collection<Greeting> greetings = greetingRepository.findAll();

        return greetings;
    }

    @Cacheable(
            value = "greetings",
            key = "#id")
    public Greeting findOne(Long id) {
        Greeting greeting = greetingRepository.findOne(id);
        return greeting;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "greetings",
            key = "#result.id")
    public Greeting create(Greeting greeting) {

        if (greeting.getId() != null) {
            //Cannot create greeting with specified ID value 
        }
        Greeting savedGreeting = greetingRepository.save(greeting);

        /*
        //Illustrate Tx rollback
        if(savedGreeting.getId() == 1L){
            throw new RuntimeException("Roll me back");
        }

         */
        return savedGreeting;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "greetings",
            key = "#greetings.id")
    public Greeting update(Greeting greeting) {
        Greeting greetingPersisted = findOne(greeting.getId());
        if (greetingPersisted == null) {

            //Cannot update greeting that hasnt been persisted
            return null;
        }

        Greeting updatedGreeting = greetingRepository.save(greeting);
        return updatedGreeting;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "greetings",
            key = "#id")
    public void delete(Long id) {
        greetingRepository.delete(id);

    }

    @Override
    @CacheEvict(
            value = "greetings",
            allEntries = true)
    public void evictCache() {
    }
}
