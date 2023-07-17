package com.taskListScheduler.toDoListScheduler.emailService;

import com.taskListScheduler.toDoListScheduler.model.Task;
import com.taskListScheduler.toDoListScheduler.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TaskReportEmailCreator {

    public String createEmailAddress(User user) {
        return user.getEmail();
    }

    public String createEmailTitle(User user) {
        return "Отчет о выполнении задач пользователем " + user.getEmail() +
                " за " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String createEmailText(User user) {
        StringBuilder emailText = new StringBuilder();
        addUncompletedTasksInfo(emailText, user.getTasks());
        addCompletedTasksInfo(emailText, user.getTasks());
        return emailText.toString();
    }


    private void addCompletedTasksInfo(StringBuilder emailText, List<Task> tasks) {
        // Добавляем в текст письма информацию о задачах, выполненных в течение предыдущих 24 часов
        List<String> completedTaskPerDayHeaders = tasks.stream()
                .filter(Task::isCompleted)
                .filter(task -> ChronoUnit.HOURS.between(task.getDateTimeComplete(), LocalDateTime.now()) < 24)
                .map(Task::getHeader)
                .toList();

        int completedTaskPerDayCount = completedTaskPerDayHeaders.size();
        if (completedTaskPerDayCount > 0) {
            emailText.append("Количество выполненных задач за сегодня ").append(completedTaskPerDayCount)
                    .append("!  За сегодня выполнены следующие задачи: ");
            for (String taskHeader : completedTaskPerDayHeaders) {
                emailText.append("- ").append(taskHeader);
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
                    .append("!  Список невыполненных задач: ");
            for (String taskHeader : uncompletedTaskHeaders) {
                emailText.append("- ").append(taskHeader);
            }
        }
    }

}