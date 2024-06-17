package com.byalif.questionsservice.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.byalif.questionsservice.DTO.KafkaDTO;

@Component
public class KafkaProducer {

//	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
//
//	@Autowired
//	NewTopic topic;
//
//	@Autowired
//	private KafkaTemplate<String, KafkaDTO> kafkaTemplate;
//
//
//	public void sendMessage(KafkaDTO kafkaDTO) {
//		LOGGER.info("Order event => %s", kafkaDTO.getQuestionDTO().toString());
//
//		Message<KafkaDTO> message = MessageBuilder.withPayload(kafkaDTO).setHeader(KafkaHeaders.TOPIC, topic.name())
//				.build();
//		
//		kafkaTemplate.send(message);
//	}
}