/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enes.ws.service;

import com.enes.ws.model.Greeting;
import com.enes.ws.util.AsyncResponse;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author enes
 */
@Service
public class EmailServiceBean implements EmailService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    
    @Override
    public Boolean send(Greeting greeting) {
        logger.info("> send");
        
        Boolean success = Boolean.FALSE;
        
        
        // Simulate method executiıon time
        long pause = 5000;
        try{
            Thread.sleep(pause);
        }
        catch(Exception e){
            //do nothing
        }
        
        logger.info("Processing time was {} seconds" , pause / 1000);
        
        success = Boolean.TRUE;
        
        logger.info("< send");
        
        return success;
        
    }

    @Async
    @Override
    public void sendAsync(Greeting greeting) {
        logger.info("> sendAsync");
        
        try{
        send(greeting);
        }catch(Exception e){
            logger.warn("Exception caught sending asynchronous mail" , e);
            
        }
        
        logger.info("< sendAsync");
    }

    @Async
    @Override
    public Future<Boolean> sendAsyncWithResult(Greeting greeting) {
        logger.info("> sendAsyncWithResult");
        
        AsyncResponse<Boolean> response = new AsyncResponse<Boolean>();
        
        try{
            Boolean success = send(greeting);
        }catch(Exception e){
            logger.warn("Exception caught sending asynchronous mail." , e);
            response.completedExceptionally(e);
        }
        
        logger.info("< sendAsyncWithResult");
        
        return response;
    }
    
}
