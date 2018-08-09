/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enes.ws.service;

import com.enes.ws.model.Greeting;
import java.util.concurrent.Future;

/**
 *
 * @author enes
 */
public interface EmailService {
    
    Boolean send(Greeting greeting);
    
    void sendAsync(Greeting greeting);
    
    Future<Boolean> sendAsyncWithResult(Greeting greeting);
    
}
