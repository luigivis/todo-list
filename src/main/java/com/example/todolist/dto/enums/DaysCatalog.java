package com.example.todolist.dto.enums;

import static com.example.todolist.dto.enums.StatusResponses.DAY_NOT_FOUND;

import com.example.todolist.util.impl.StatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DaysCatalog {

    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private final String name;

    public static DaysCatalog getByName(String name) {
        for (DaysCatalog day : DaysCatalog.values()) {
            if (day.name().equals(name)) {
                return day;
            }
        }
        throw new StatusException(DAY_NOT_FOUND.get());
    }

}
