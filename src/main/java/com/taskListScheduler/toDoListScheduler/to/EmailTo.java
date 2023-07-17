package com.taskListScheduler.toDoListScheduler.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailTo  implements Serializable {
    private String recipientAddress;
    private String title;
    private String text;
}
