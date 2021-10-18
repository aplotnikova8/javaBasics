package com.plotnikova.models;

import com.plotnikova.enums.Course;
import com.plotnikova.utils.DateTimeCalculator;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.plotnikova.utils.DateTimeCalculator.beginWorking;

@Getter
public class Student {

    private String name;
    private String specialization;
    private List<Course> program;
    private LocalDateTime programStart;
    private LocalDateTime programEnd;

    public final LocalDate firstEducationalDay = LocalDate.of(2021, Month.AUGUST, 30);

    private void appointCourses(List<Course> courses) {
        this.program = new ArrayList<>();
        courses.forEach(course -> program.add(course));
    }

    public Student(String name, String specialization, Course... courses) {
        this.name = name;
        this.specialization = specialization;
        appointCourses(Arrays.asList(courses));
        this.programStart = firstEducationalDay.atTime(beginWorking);
        this.programEnd = DateTimeCalculator.calculateProgramEnd(this);
    }

}
