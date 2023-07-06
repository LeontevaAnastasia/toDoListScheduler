package com.taskListScheduler.toDoListScheduler.service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducer {
      private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.exchange}")
    private String topicExchangeName;

    @Value("${rabbit.routing-key}")
    private String routingKey;



    private static final Logger log = LoggerFactory.getLogger(RabbitProducer.class);

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message) {
            try {
                rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
                log.info("Scheduled email messages has been sent to RabbitMQ queue");
            } catch (Exception exception) {
                log.error("Scheduled emails not sent {}", exception.getMessage());
            }
        }
}
