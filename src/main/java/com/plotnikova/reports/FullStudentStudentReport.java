package com.plotnikova.reports;

import com.plotnikova.converters.DateTimeConverter;
import com.plotnikova.converters.DurationConverter;
import com.plotnikova.models.Student;
import com.plotnikova.utils.DateTimeCalculator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

public class FullStudentStudentReport implements StudentReport {

    @Override
    public void generateReport(List<Student> students, LocalDateTime reportDateTime) {
        System.out.println(String.format("Full report. Generating report date - %1$s", DateTimeConverter.convertDateTimeToString(reportDateTime)));
        students.forEach(student -> {
            System.out.println("---------");
            System.out.println(showStudentFullInfo(student, reportDateTime));});
    }

    private String showStudentFullInfo(Student student, LocalDateTime reportDateTime) {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(student.getName());
        joiner.add(String.format("Working time: from %1$s to %2$s", DateTimeCalculator.beginWorking, DateTimeCalculator.finishWorking));
        joiner.add("Program name - " + student.getSpecialization());
        joiner.add("Program duration in hours - " + DateTimeCalculator.calculateTotalProgramDurationInHours(student));
        joiner.add("Start date - " + DateTimeConverter.convertDateTimeToString(student.getProgramStart()));
        joiner.add("End date - " + DateTimeConverter.convertDateTimeToString(student.getProgramEnd()));
        if (reportDateTime.isBefore(student.getProgramEnd())) {
            joiner.add(String.format("Training is not finished. %1$s are left until the end.",
                    DurationConverter.convertDurationToString(DateTimeCalculator.calculateDurationBetweenReportAndDeadline(student, reportDateTime))));
        }  else {
            joiner.add(String.format("Training completed. %1$s have passed since the end.",
                    DurationConverter.convertDurationToString(DateTimeCalculator.calculateDurationBetweenReportAndDeadline(student, reportDateTime))));
        }
        return joiner.toString();
    }
}
