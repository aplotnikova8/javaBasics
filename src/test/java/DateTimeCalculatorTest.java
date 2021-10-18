import com.plotnikova.models.Student;
import com.plotnikova.utils.DateTimeCalculator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static com.plotnikova.enums.Course.*;

public class DateTimeCalculatorTest {

    private static Student student;

    @DataProvider(name = "reportDate")
    public Object[][] reportDataProvider() {
        return new Object[][]{
                {LocalDateTime.of(2021, 9, 1, 11, 0), Duration.ofHours(15)},// education is still going
                {LocalDateTime.of(2021, 8, 28, 21, 0), Duration.ofHours(32)},// education has not been started yet and report date is weekend
                {LocalDateTime.of(2021, 9, 13, 12, 0), Duration.ofHours(50)},// education has been finished
        };
    }

    @DataProvider(name = "students")
    public Object[][] studentDataProvider() {
        return new Object[][]{
                {new Student("Abramova Mariya", "QE", AGILE), LocalDateTime.of(2021,8,30,17,0)}, // deadline in the same date as a start
                {new Student("Simonov Viktor", "Backend developer", JDBC, SPRING), LocalDateTime.of(2021,9,3,18,0)},// deadline on the same week
                {new Student("Platonov Platon", "Basic", JAVA, JDBC, SPRING), LocalDateTime.of(2021,9,7,18,0)} // deadline on the next week
        };
    }

    @BeforeClass
    public static void createStudent() {
        student = new Student("Ivanov Ivan", "QE", JAVA, SPRING);
    }

    @Test
    public void shouldHasProgramDurationAsHoursSum() {
        Assert.assertEquals(32, DateTimeCalculator.calculateTotalProgramDurationInHours(student));
    }

    @Test(dataProvider = "students")
    public void shouldHasProgramEnd(Student testStudent, LocalDateTime end) {
        Assert.assertEquals(testStudent.getProgramEnd().toLocalDate(), end.toLocalDate());
        Assert.assertEquals(testStudent.getProgramEnd().toLocalTime(), end.toLocalTime());
    }

    @Test(dataProvider = "reportDate")
    public void shouldCalculateDurationBetweenReportAndDeadline(LocalDateTime reportDate, Duration difference) {
        Assert.assertEquals(DateTimeCalculator.calculateDurationBetweenReportAndDeadline(student, reportDate).toHours(), difference.toHours());
    }
}
