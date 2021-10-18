package com.plotnikova.reports;

import com.plotnikova.models.Student;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentReport {

    void generateReport(List<Student> students, LocalDateTime reportDateTime);
}
