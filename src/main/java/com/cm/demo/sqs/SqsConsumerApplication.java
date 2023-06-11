package com.cm.demo.sqs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@SpringBootApplication
public class SqsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqsConsumerApplication.class, args);
	}

	@Value("${app.config.aws.access_key_id}")
	private String awsAccessKeyId;
	@Value("${app.config.aws.secret_key_id}")
	private String awsSecretKeyId;

	@Bean
	public AmazonSQS amazonSQSClient() {
		/*
		 * BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsAccessKeyId,
		 * awsSecretKeyId); return AmazonSQSClientBuilder.standard().withCredentials(new
		 * AWSStaticCredentialsProvider(awsCredentials))
		 * .withRegion("us-east-1").build();
		 */
		return AmazonSQSClientBuilder.standard().withRegion("us-east-1").build();
	}
}
