package com.taskListScheduler.toDoListScheduler.service;

import com.taskListScheduler.toDoListScheduler.model.User;

public interface EmailCreator {

     String createEmailAddress(User user);
     String createEmailTitle(User user);
     String createEmailText(User user);
}
