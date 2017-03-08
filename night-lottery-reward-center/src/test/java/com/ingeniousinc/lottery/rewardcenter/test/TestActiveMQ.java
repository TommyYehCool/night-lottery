package com.ingeniousinc.lottery.rewardcenter.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;

import com.ingeniousinc.entity.cnst.jms.QueueName;
import com.ingeniousinc.entity.cnst.jms.TopicName;
import com.ingeniousinc.lottery.rewardcenter.Application;
import com.ingeniousinc.lottery.rewardcenter.jms.QueueProducer;
import com.ingeniousinc.lottery.rewardcenter.jms.TopicProducer;

@RunWith(SpringRunner.class)
@SpringBootTest(
	classes = Application.class,
	webEnvironment = WebEnvironment.RANDOM_PORT
)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestActiveMQ {
	
	@Rule
	public OutputCapture outputCapture = new OutputCapture(); 
	
	@Autowired
	private QueueProducer queueProducer;
	
	@Autowired
	private TopicProducer topicProducer;
	
	@Test
	public void test_1_SendMsgToTestingQ() {
		String msg = "Msg to testingQ";
		queueProducer.sendToTestingQ(msg);
		assertThat(outputCapture.toString().contains("Send msg: <" + msg + "> to queue: <" + QueueName.TESTING_Q + "> succeed")).isTrue();
	}
	
	@Test
	public void test_2_SendMsgToHahaQ() {
		String msg = "Msg to hahaQ";
		queueProducer.sendToHahaQ(msg);
		assertThat(outputCapture.toString().contains("Send msg: <" + msg + "> to queue: <" + QueueName.HAHA_Q + "> succeed")).isTrue();
	}
	
	@Test
	public void test_3_SendMsgToTestingT() {
		String msg = "Msg to testingT";
		topicProducer.sendToTestingT(msg);
		assertThat(outputCapture.toString().contains("Send msg: <" + msg + "> to topic: <" + TopicName.TESTING_T + "> succeed")).isTrue();
	}
}
