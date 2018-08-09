/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.enes.ws.util;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author enes
 */
public class AsyncResponse<V> implements Future<V> {

    private V value;
    private Exception executionException;
    private boolean isCompletedExceptionally = false;
    private boolean isCancelled = false;
    private boolean isDone = false;
    private long checkCompletedInterval = 100;

    public AsyncResponse() {

    }

    public AsyncResponse(V val) {
        this.value = val;
        this.isDone = true;
    }

    public AsyncResponse(Throwable ex) {
        this.executionException = new ExecutionException(ex);
        this.isCompletedExceptionally = true;
        this.isDone = true;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        this.isCancelled = true;
        this.isDone = true;

        return false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    public boolean isCompletedExceptionally() {
        return this.isCompletedExceptionally;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        block(0);
        if (isCancelled()) {
            throw new CancellationException();

        }
        if (isCompletedExceptionally()) {
            throw new ExecutionException(this.executionException);
        }
        if(isDone()){
            return this.value;
        }
        
        throw new InterruptedException();
    }
    
    public boolean complete(V val){
        this.value = val;
        this.isDone = true;
        
        return true;
    }
    
    public boolean completedExceptionally(Throwable ex){
        this.value = null;
        this.executionException = new ExecutionException(ex);
        this.isDone = true;
        
        return true;
    }
    
    public void setCheckCompletedInterval(long millis){
        this.checkCompletedInterval = millis;
    }
    
    
    private void block (long timeout) throws InterruptedException{
        long start = System.currentTimeMillis();
        
        
        while(!isDone() && !isCancelled()){
            if(timeout > 0){
                long now = System.currentTimeMillis();
                if(now > start + timeout){
                    break;
                }
            }
            
            Thread.sleep(checkCompletedInterval);
        }
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        long timeoutInMillis = unit.toMillis(timeout);
        
        block(timeoutInMillis);
        
        if (isCancelled()) {
            throw new CancellationException();

        }
        if (isCompletedExceptionally()) {
            throw new ExecutionException(this.executionException);
        }
        if(isDone()){
            return this.value;
        }
        
        throw new InterruptedException();
        
        
    }

}
