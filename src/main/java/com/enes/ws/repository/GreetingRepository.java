package com.enes.ws.repository;


import com.enes.ws.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author enes
 */
@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long>{
    
}
