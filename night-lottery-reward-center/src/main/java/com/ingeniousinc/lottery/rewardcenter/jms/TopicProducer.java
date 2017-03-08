package com.ingeniousinc.lottery.rewardcenter.jms;

import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.ingeniousinc.entity.cnst.jms.TopicName;

@Component
public class TopicProducer {
	private static final Logger logger = LoggerFactory.getLogger(TopicProducer.class);
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Autowired
	private Topic testingT;
	
	public void sendToTestingT(String msg) {
		jmsMessagingTemplate.convertAndSend(testingT, msg);
		logger.info("Send msg: <{}> to topic: <{}> succeed", msg, TopicName.TESTING_T);
	}
}
