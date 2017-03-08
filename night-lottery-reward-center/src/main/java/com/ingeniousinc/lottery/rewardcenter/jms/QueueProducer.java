package com.ingeniousinc.lottery.rewardcenter.jms;

import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.ingeniousinc.entity.cnst.jms.QueueName;

@Component
public class QueueProducer {
	private static final Logger logger = LoggerFactory.getLogger(QueueProducer.class);
	
	@Autowired
	private JmsMessagingTemplate jmsMessageTemplate;
	
	@Autowired
	private Queue testingQ;
	
	@Autowired
	private Queue hahaQ;
	
	public void sendToTestingQ(String msg) {
		jmsMessageTemplate.convertAndSend(testingQ, msg);
		logger.info("Send msg: <{}> to queue: <{}> succeed", msg, QueueName.TESTING_Q);
	}
	
	public void sendToHahaQ(String msg) {
		jmsMessageTemplate.convertAndSend(hahaQ, msg);
		logger.info("Send msg: <{}> to queue: <{}> succeed", msg, QueueName.HAHA_Q);
	}
	
}
