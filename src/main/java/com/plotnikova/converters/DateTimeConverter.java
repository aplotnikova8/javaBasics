package com.plotnikova.converters;

import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
@Log
public class DateTimeConverter {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy, EEEE, HH:mm");
    public static DateTimeFormatter dateTimeFormatterForConsole = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static String convertDateTimeToString(LocalDateTime dateTime) {
        try {
            return String.format(dateTime.format(dateTimeFormatter));
        } catch (DateTimeException exception) {
            log.warning("Incorrect date and time! Date and time for NOW was chosen");
            return String.format(LocalDateTime.now().format(dateTimeFormatter));
        }

    }
}
