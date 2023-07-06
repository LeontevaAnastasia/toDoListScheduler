package com.taskListScheduler.toDoListScheduler.service;

import com.taskListScheduler.toDoListScheduler.model.Task;
import com.taskListScheduler.toDoListScheduler.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TaskReportEmailCreator implements EmailCreator {

    @Override
    public String createEmailAddress(User user) {
        return user.getEmail();
    }

    @Override
    public String createEmailTitle(User user) {
        return "Отчет о выполнении задач пользователем " + user.getEmail() +
                "за " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String createEmailText(User user) {
        StringBuilder emailText = new StringBuilder();
        addUncompletedTasksInfo(emailText, user.getTasks());
        addCompletedTasksInfo(emailText, user.getTasks());
        return emailText.toString();
    }


    private void addCompletedTasksInfo(StringBuilder emailText, List<Task> tasks) {
        // Добавляем в текст письма информацию о задачах, выполненных в течение предыдущих 24 часов
        List<String> completedTaskPerDayHeaders = tasks.stream()
                .filter(task -> ChronoUnit.HOURS.between(task.getDateTimeComplete(), LocalDateTime.now()) < 24)
                .filter(Task::isCompleted)
                .map(Task::getHeader)
                .toList();

        int completedTaskPerDayCount = completedTaskPerDayHeaders.size();
        if (completedTaskPerDayCount > 0) {
            emailText.append("Количество выполненных задач за сегодня ").append(completedTaskPerDayCount)
                    .append("!\n\n За сегодня выполнены следующие задачи:\n");
            for (String taskHeader : completedTaskPerDayHeaders) {
                emailText.append("- ").append(taskHeader).append("\n");
            }
        }
    }

    private void addUncompletedTasksInfo(StringBuilder emailText, List<Task> tasks) {
        // Добавляем информацию о невыполенных задачах в текст письма
        List<String> uncompletedTaskHeaders = tasks.stream()
                .filter(task -> !task.isCompleted())
                .map(Task::getHeader)
                .toList();

        int uncompletedTaskCount = uncompletedTaskHeaders.size();
        if (uncompletedTaskCount > 0) {
            emailText.append("Общее количество невыполненных задач: ").append(uncompletedTaskCount)
                    .append("!\n\nСписок невыполненных задач:\n");
            for (String taskHeader : uncompletedTaskHeaders) {
                emailText.append("- ").append(taskHeader).append("\n");
            }
        }
    }

}