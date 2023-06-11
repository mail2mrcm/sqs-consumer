package com.cm.demo.sqs;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;

@RestController
@RequestMapping("/sqs")
public class SQSConsumerController {
	private static final Logger logger = LogManager.getLogger(SQSConsumerController.class);
	@Autowired
	private AmazonSQS amazonSQSClient;

	@GetMapping("/get")
	public List<Message> getMessage() {
		try {
			GetQueueUrlResult queueUrl = amazonSQSClient.getQueueUrl("my-simple-queue");
			logger.info("Reading SQS Queue done: URL {}", queueUrl.getQueueUrl());
			ReceiveMessageResult result = amazonSQSClient.receiveMessage(queueUrl.getQueueUrl());
			return result.getMessages();
		} catch (Exception e) {
			logger.error("Queue does not exist {}", e.getMessage());
		}
		return new ArrayList<Message>();
	}

}
