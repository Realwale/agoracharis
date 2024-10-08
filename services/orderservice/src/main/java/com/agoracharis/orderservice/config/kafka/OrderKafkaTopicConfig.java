package com.agoracharis.orderservice.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class OrderKafkaTopicConfig {

    @Bean
    public NewTopic newTopic(){
        return TopicBuilder.name("order-topic").build();
    }
}
