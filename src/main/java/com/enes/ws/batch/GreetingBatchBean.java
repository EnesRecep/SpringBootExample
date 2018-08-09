/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enes.ws.batch;

import com.enes.ws.model.Greeting;
import com.enes.ws.service.GreetingService;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author enes
 */
@Component
public class GreetingBatchBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GreetingService greetingService;

    @Scheduled(
            cron = "0,30 * * * * *")
    public void cronJob() {
        logger.info("> cronjob");
        Collection<Greeting> greetings = greetingService.findAll();
        
        logger.info("There are " + greetings.size() + " greetings in the data store.");
        
        logger.info("< cronjob");
    }

}
