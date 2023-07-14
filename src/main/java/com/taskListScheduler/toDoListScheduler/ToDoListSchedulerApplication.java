package com.taskListScheduler.toDoListScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToDoListSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoListSchedulerApplication.class, args);
	}

}
