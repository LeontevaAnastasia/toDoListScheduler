package com.taskListScheduler.toDoListScheduler.emailService.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitProducer {
      private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.exchange}")
    private String topicExchangeName;
    @Value("${rabbit.routing-key}")
    private String routingKey;

    public void sendMessage(String message) {
            try {
                rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
                log.info("Scheduled email messages has been sent to RabbitMQ queue");
            } catch (Exception exception) {
                log.error("Scheduled emails not sent {}", exception.getMessage());
            }
        }
}
