package com.example.todolist.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.example.todolist.dto.times.Time.*;

@Service
@Slf4j
public class TaskSchedulingNotification {
    //@Scheduled(fixedRate = FIVE_SECOND)
    //public void executeScheduledTask() {
    //    log.info("Test scheduling");
    //}
//
    //@Scheduled(fixedRate = ONE_MINUTE)
    //public void sendTaskRemember() {
    //    // ToDo Create task remember notification
    //}
}
