package com.example.todolist.util.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ShortUuid {

    public static String generate() {
        var timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHMMddyymm-ss"));
        return timestamp + "-" + String.format("%04d", (int) (Math.random() * 10000));
    }

    public static String generateLong(Long id) {
        return (ObjectUtils.isEmpty(id) ? "":id) + String.format("%04d", (int) (Math.random() * 10000));
    }

}
