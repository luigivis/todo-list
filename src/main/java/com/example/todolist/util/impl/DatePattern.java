package com.example.todolist.util.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public enum DatePattern {
  MM_dd_yyyy("MM/dd/yyyy"),
  MMMM_dd_yyyy("MMMM dd, yyyy"),
  dd_MM_yyyy("dd-MM-yyyy"),
  EEE_MMM_d_yyyy("EEE, MMM d yyyy"),
  yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
  MMM_d_yyyy_h_mm_a("MMM d, yyyy h:mm a"),
  EEEE_MMMM_dd_yyyy("EEEE, MMMM dd, yyyy"),
  dd_MMM_yyyy("dd MMM yyyy"),
  yyyy_MM_dd("yyyy/MM/dd"),
  d_M_yy("d/M/yy");

  private final String pattern;

  public static String returnDate(DatePattern format) {
    return DateTimeFormatter.ofPattern(format.getPattern()).format(LocalDateTime.now());
  }
}
