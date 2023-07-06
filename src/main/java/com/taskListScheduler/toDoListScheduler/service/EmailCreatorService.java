package com.taskListScheduler.toDoListScheduler.service;

import com.taskListScheduler.toDoListScheduler.model.User;
import com.taskListScheduler.toDoListScheduler.model.dto.EmailDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailCreatorService {

    EmailCreator emailCreator;

    public List<EmailDto> getEmailMessages(List<User> users) {
         List<EmailDto>emailMessages = new ArrayList<>();
        for (User user:users) {
            if (user.getTasks().size() > 0) {
                EmailDto emailDTO = new EmailDto();
                emailDTO.setRecipientAddress(emailCreator.createEmailAddress(user));
                emailDTO.setTitle(emailCreator.createEmailTitle(user));
                emailDTO.setText(emailCreator.createEmailText(user));
                emailMessages.add(emailDTO);
            }
        }
        return emailMessages;
    }

}
