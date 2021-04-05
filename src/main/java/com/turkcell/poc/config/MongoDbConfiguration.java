/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turkcell.poc.config;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 *
 * @author kissoid
 */
@EnableReactiveMongoRepositories
public class MongoDbConfiguration extends AbstractReactiveMongoConfiguration{
    

    @Bean
    public MongoClient mongoClient() {
        //int port = environment.getProperty("local.mongo.port", Integer.class);
        return MongoClients.create(String.format("mongodb://localhost:%d", 27017));
    }

    @Override
    protected String getDatabaseName() {
        return "amaris";
    }

}
