package com.ingeniousinc.lottery.frontend.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.ingeniousinc.entity.cnst.jms.QueueName;

@Component
public class QueueConsumer {
	private static final Logger logger = LoggerFactory.getLogger(QueueConsumer.class);

	private final String JMS_LISTENER_CONTAINER_QUEUE = "jmsListenerContainerQueue";
	
	@JmsListener(destination = QueueName.TESTING_Q, containerFactory = JMS_LISTENER_CONTAINER_QUEUE)
	public void receiveFromTestingQ(String text) {
		logger.info("Received message: <{}> from queue: <{}>", text, QueueName.TESTING_Q);
	}
	
	@JmsListener(destination = QueueName.HAHA_Q, containerFactory = JMS_LISTENER_CONTAINER_QUEUE)
	public void receiveFromHahaQ(String text) {
		logger.info("Received message: <{}> from queue: <{}>", text, QueueName.HAHA_Q);
	}
}
