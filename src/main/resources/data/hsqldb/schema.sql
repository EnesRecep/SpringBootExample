/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  enes
 * Created: Aug 9, 2018
 */

DROP TABLE Greeting IF EXISTS;

CREATE TABLE Greeting(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY( START WITH 1, INCREMENT BY 1) NOT NULL,
    text VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);