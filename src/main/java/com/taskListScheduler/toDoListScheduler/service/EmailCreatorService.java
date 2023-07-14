package com.taskListScheduler.toDoListScheduler.service;

import com.taskListScheduler.toDoListScheduler.model.User;
import com.taskListScheduler.toDoListScheduler.to.EmailDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmailCreatorService {

    private final TaskReportEmailCreator emailCreator;

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
