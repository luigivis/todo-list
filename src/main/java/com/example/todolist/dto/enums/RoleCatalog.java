package com.example.todolist.dto.enums;

import static com.example.todolist.dto.enums.StatusResponses.ROLE_NOT_FOUND;

import com.example.todolist.util.impl.StatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public enum RoleCatalog {
    ADMIN(1, "ADMIN"),
    EXTERNAL_CLIENT(2, "EXTERNAL-CLIENT"),
    CLIENT(3, "CLIENT");

    private int value;

    private String description;

    public static RoleCatalog getByValue(int value) {
        for (RoleCatalog role : RoleCatalog.values()) {
            if (role.getValue() == value) {
                return role;
            }
        }
    throw new StatusException(ROLE_NOT_FOUND.get());
    }

}