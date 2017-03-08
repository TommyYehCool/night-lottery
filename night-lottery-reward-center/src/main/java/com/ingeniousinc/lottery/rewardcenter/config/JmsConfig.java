package com.ingeniousinc.lottery.rewardcenter.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import com.ingeniousinc.entity.cnst.jms.QueueName;
import com.ingeniousinc.entity.cnst.jms.TopicName;

@Configuration
@EnableJms
public class JmsConfig {
	
    @Bean
    public Topic testingT() {
    	return new ActiveMQTopic(TopicName.TESTING_T);
    }
    
    @Bean 
    public Queue testingQ() {
    	return new ActiveMQQueue(QueueName.TESTING_Q);
    }
    
    @Bean 
    public Queue hahaQ() {
    	return new ActiveMQQueue(QueueName.HAHA_Q);
    }

}
