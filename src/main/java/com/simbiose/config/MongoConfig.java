package com.simbiose.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.simbiose.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1");
    }

    @Override
    protected String getDatabaseName() {
        return "mercadolivre";
    }

}
