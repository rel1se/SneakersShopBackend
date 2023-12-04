package ru.rel1se.sneakersshop.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatusCode {
    private int status;

    public static StatusCode create(int statusCode) {
        return new StatusCode(statusCode);
    }
}
