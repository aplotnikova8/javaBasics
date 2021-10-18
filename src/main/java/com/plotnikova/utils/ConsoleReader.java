package com.plotnikova.utils;

import com.plotnikova.converters.DateTimeConverter;
import lombok.experimental.UtilityClass;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@UtilityClass
@Log
public class ConsoleReader {

    public LocalDateTime readReportDate() {
        System.out.println("Enter report date in format dd.MM.yyyy HH:mm");
        Scanner scannerDate = new Scanner(System.in);
        try {
            return LocalDateTime.parse(scannerDate.nextLine(), DateTimeConverter.dateTimeFormatterForConsole);
        } catch (DateTimeParseException exception) {
            log.warning("Could not parse to dd.MM.yyyy HH:mm format. Report will be generated for today at 10:00");
            return LocalDate.now().atTime(10, 0);
        }
    }

    public String readReportType() {
        System.out.println("Enter report type: 0 - short; 1 - full");
        Scanner scannerReportType = new Scanner(System.in);
        try {
            return scannerReportType.nextLine();
        } catch (IllegalStateException exception) {
            log.warning("Could not read report type. Short report will be applied");
            return "0";
        }
    }
}
