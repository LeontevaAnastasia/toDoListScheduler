package com.taskListScheduler.toDoListScheduler.emailService;

import com.taskListScheduler.toDoListScheduler.model.User;
import com.taskListScheduler.toDoListScheduler.to.EmailTo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmailCreatorService {

    private final TaskReportEmailCreator emailCreator;

    public List<EmailTo> getEmailMessages(List<User> users) {
         List<EmailTo>emailMessages = new ArrayList<>();
        for (User user:users) {
            if (user.getTasks().size() > 0) {
                EmailTo emailTo = new EmailTo();
                emailTo.setRecipientAddress(emailCreator.createEmailAddress(user));
                emailTo.setTitle(emailCreator.createEmailTitle(user));
                emailTo.setText(emailCreator.createEmailText(user));
                emailMessages.add(emailTo);
            }
        }
        return emailMessages;
    }

}
