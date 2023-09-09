package com.example.todolist.dto.times;

public class Time {

  public static final long ONE_SECOND = 1000L;
  public static final long TWO_SECOND = 2000L;
  public static final long TREE_SECOND = 3000L;
  public static final long FIVE_SECOND = 5000L;
  public static final long TEN_SECOND = 10000L;

  public static final long ONE_MINUTE = 60 * ONE_SECOND;
  public static final long TWO_MINUTES = 2 * ONE_MINUTE;
  public static final long THREE_MINUTES = 3 * ONE_MINUTE;
  public static final long FOUR_MINUTES = 4 * ONE_MINUTE;
  public static final long FIVE_MINUTES = 5 * ONE_MINUTE;
  public static final long TEN_MINUTES = 10 * ONE_MINUTE;

  public static final long ONE_HOUR = 60 * ONE_MINUTE;
  public static final long TWO_HOURS = 2 * ONE_HOUR;
  public static final long THREE_HOURS = 3 * ONE_HOUR;
  public static final long FOUR_HOURS = 4 * ONE_HOUR;
  public static final long FIVE_HOURS = 5 * ONE_HOUR;
  public static final long TEN_HOURS = 10 * ONE_HOUR;

  public static final long ONE_DAY = 24 * ONE_HOUR;
  public static final long TWO_DAYS = 2 * ONE_DAY;
  public static final long THREE_DAYS = 3 * ONE_DAY;
  public static final long FOUR_DAYS = 4 * ONE_DAY;
  public static final long FIVE_DAYS = 5 * ONE_DAY;
  public static final long TEN_DAYS = 5 * ONE_DAY;

  public static Long seconds(Long seconds) {
    return 1000 + seconds;
  }

  public static long secondsToMilliseconds(long seconds) {
    return seconds * ONE_SECOND;
  }

  public static long minutesToMilliseconds(long minutes) {
    return minutes * ONE_MINUTE;
  }

  public static long hoursToMilliseconds(long hours) {
    return hours * ONE_HOUR;
  }

  public static long daysToMilliseconds(long days) {
    return days * ONE_DAY;
  }
}
