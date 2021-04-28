package com.brook.entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ioc {
    @Bean
    public User user() {
        return new User("xx");
    }
}