package com.byalif.questionsservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	private static final String TOPIC = "inquiry_requests";
//
//	@Bean
//	public NewTopic topic() {
//		return TopicBuilder.name(TOPIC).build();
//	}

}
