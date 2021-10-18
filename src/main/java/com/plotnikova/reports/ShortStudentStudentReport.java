package com.plotnikova.reports;

import com.plotnikova.converters.DateTimeConverter;
import com.plotnikova.converters.DurationConverter;
import com.plotnikova.models.Student;
import com.plotnikova.utils.DateTimeCalculator;

import java.time.LocalDateTime;
import java.util.List;


public class ShortStudentStudentReport implements StudentReport {

    @Override
    public void generateReport(List<Student> students, LocalDateTime reportDateTime) {
        System.out.println(String.format("Short report. Generating report date - %1$s", DateTimeConverter.convertDateTimeToString(reportDateTime)));
        students.forEach(student -> System.out.println(showStudentShortInfo(student, reportDateTime)));
    }

    private String showStudentShortInfo(Student student, LocalDateTime reportDateTime) {
        if (reportDateTime.isBefore(student.getProgramEnd())) {
            return String.format("%1$s (%2$s) - Training is not finished. %3$s are left until the end.",
                    student.getName(),
                    student.getSpecialization(),
                    DurationConverter.convertDurationToString(DateTimeCalculator.calculateDurationBetweenReportAndDeadline(student, reportDateTime)));
        } else {
            return String.format("%1$s (%2$s) - Training completed. %3$s have passed since the end.",
                    student.getName(),
                    student.getSpecialization(),
                    DurationConverter.convertDurationToString(DateTimeCalculator.calculateDurationBetweenReportAndDeadline(student, reportDateTime)));
        }
    }

}
