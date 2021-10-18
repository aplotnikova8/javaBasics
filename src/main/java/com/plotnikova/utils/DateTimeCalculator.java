package com.plotnikova.utils;

import com.plotnikova.enums.Course;
import com.plotnikova.models.Student;
import lombok.experimental.UtilityClass;

import java.time.*;
import java.util.stream.Stream;


@UtilityClass
public class DateTimeCalculator {

    public static final LocalTime beginWorking = LocalTime.of(10, 0);
    public static final LocalTime finishWorking = LocalTime.of(18, 0);
    public static final int workingHoursDuration = Duration.between(beginWorking, finishWorking).toHoursPart();

    public static LocalDateTime calculateProgramEnd(Student student) {
        var totalTime = calculateTotalProgramDurationInHours(student);
        LocalDate nowDate = student.getProgramStart().toLocalDate();

        while (totalTime > workingHoursDuration) {
            if (nowDate.getDayOfWeek() != DayOfWeek.SATURDAY && nowDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                totalTime -= workingHoursDuration;
            }
            nowDate = nowDate.plusDays(1);
        }
        return nowDate.atTime(beginWorking).plusHours(totalTime);
    }

    public Duration calculateDurationBetweenReportAndDeadline(Student student, LocalDateTime reportDateTime) {
        if (reportDateTime.isBefore(student.getProgramEnd())) {
            return calculateDurationInDaysAndHour(reportDateTime, student.getProgramEnd());
        } else {
            return calculateDurationInDaysAndHour(student.getProgramEnd(), reportDateTime);
        }
    }

    public long calculateTotalProgramDurationInHours(Student student) {
        return student.getProgram().stream()
                .mapToInt(Course::getDuration)
                .sum();
    }

    private Duration calculateDurationInDaysAndHour(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.toLocalDate().equals(endDate.toLocalDate())) {
            return calculateDurationInHours(startDate, endDate);
        }

        Duration totalTime = Duration.ZERO;

        if (!startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            if (startDate.toLocalTime().isBefore(finishWorking)) {
                if (startDate.toLocalTime().isAfter(beginWorking)) {
                    totalTime = Duration.between(startDate.toLocalTime(), finishWorking);
                } else {
                    totalTime = Duration.between(beginWorking, finishWorking);
                }
            }
        }

        if (!endDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !endDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            if (endDate.toLocalTime().isBefore(finishWorking)) {
                if (startDate.toLocalTime().isAfter(beginWorking)) {
                    totalTime = Duration.between(beginWorking, endDate.toLocalTime());
                }
            } else {
                totalTime = totalTime.plus(Duration.between(beginWorking, finishWorking));
            }
        }

        var fullWorkingDays = getFullDays(startDate, endDate)
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                .filter(date -> !date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                .count();

        return totalTime.plus(Duration.ofHours(fullWorkingDays * workingHoursDuration));
    }

    private Duration calculateDurationInHours(LocalDateTime startDate, LocalDateTime endDate) {
        if (endDate.toLocalTime().isBefore(finishWorking) || endDate.toLocalTime().equals(finishWorking)) {
            if (startDate.toLocalTime().isAfter(beginWorking)) {
                return Duration.between(startDate.toLocalTime(), endDate.toLocalTime());
            } else {
                return Duration.between(beginWorking, endDate.toLocalTime());
            }
        } else {
            return Duration.ZERO;
        }
    }

    private Stream<LocalDate> getFullDays(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isBefore(endDate)) {
            return startDate.toLocalDate().plusDays(isWorkingStartDay(startDate)).datesUntil(endDate.toLocalDate());
        }
        return endDate.toLocalDate().plusDays(isWorkingStartDay(startDate)).datesUntil(startDate.toLocalDate());
    }

    private int isWorkingStartDay(LocalDateTime startDate) {
        if (!startDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !startDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            return 1;
        } else {
            return 0;
        }
    }

}
