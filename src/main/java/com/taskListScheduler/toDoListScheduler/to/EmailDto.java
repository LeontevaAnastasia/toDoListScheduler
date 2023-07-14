package com.taskListScheduler.toDoListScheduler.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {

    private String recipientAddress;
    private String title;
    private String text;
}
