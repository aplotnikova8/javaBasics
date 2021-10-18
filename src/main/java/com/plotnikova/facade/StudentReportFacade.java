package com.plotnikova.facade;

import com.plotnikova.models.Student;
import com.plotnikova.reports.FullStudentStudentReport;
import com.plotnikova.reports.StudentReport;
import com.plotnikova.reports.ShortStudentStudentReport;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
public class StudentReportFacade {

    public void callStudentReport(String reportType, List<Student> students, LocalDateTime reportDateTime) {
        StudentReport studentReport;
        if (reportType.equalsIgnoreCase("0") || reportType.isEmpty()) {
            studentReport = new ShortStudentStudentReport();
        } else {
            studentReport = new FullStudentStudentReport();
        }
        studentReport.generateReport(students, reportDateTime);
    }

}
