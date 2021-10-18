package com.plotnikova;

import com.plotnikova.facade.StudentReportFacade;
import com.plotnikova.models.Student;
import com.plotnikova.utils.ConsoleReader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.plotnikova.enums.Course.*;

public class ApplicationRunner {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Petrov Petr", "QE", TEST_DESIGN));
        students.add(new Student("Sokolov Alex ", "BACKEND DEVELOPER", JAVA, SPRING));

        LocalDateTime reportDate = ConsoleReader.readReportDate();
        String reportType = ConsoleReader.readReportType();

        StudentReportFacade reportFacade = new StudentReportFacade();
        reportFacade.callStudentReport(reportType, students, reportDate);
    }

}
