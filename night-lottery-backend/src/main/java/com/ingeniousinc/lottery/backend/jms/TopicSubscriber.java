package com.ingeniousinc.lottery.backend.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ingeniousinc.entity.cnst.jms.TopicName;

@Component
public class TopicSubscriber {
	private static final Logger logger = LoggerFactory.getLogger(TopicSubscriber.class);
	
	@JmsListener(destination = TopicName.TESTING_T, containerFactory = "jmsListenerContainerTopic")
	public void receiveFromTestingT(String text) {
		logger.info("Received message: <{}> from topic: <{}>", text, TopicName.TESTING_T);
	}
	
}
