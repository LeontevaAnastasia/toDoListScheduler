package com.taskListScheduler.toDoListScheduler.emailService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskListScheduler.toDoListScheduler.model.User;
import com.taskListScheduler.toDoListScheduler.to.EmailTo;
import com.taskListScheduler.toDoListScheduler.repository.UserRepository;
import com.taskListScheduler.toDoListScheduler.emailService.rabbitmq.RabbitProducer;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SendEmailsScheduler {

    private final UserRepository userRepository;
    private final EmailCreatorService emailCreatorService;
    private final RabbitProducer rabbitProducer;

    private static final Logger log = LoggerFactory.getLogger(RabbitProducer.class);


    // every midnight
      @Scheduled(cron = "0 0 0 * * *")
        public void sendEmails() {
            List<User> users = userRepository.findAll();
            List<EmailTo> emailMessages = emailCreatorService.getEmailMessages(users);

            ObjectMapper mapper = new ObjectMapper();
            try {
                for (EmailTo emailMessage:emailMessages) {
                    String json = mapper.writeValueAsString(emailMessage);
                    rabbitProducer.sendMessage(json);
                }
            }catch (JsonProcessingException exception) {
                log.error("Scheduled emails not sent" + exception.getMessage());
            }
        }

}
