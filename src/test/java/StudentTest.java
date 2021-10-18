import com.plotnikova.models.Student;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.plotnikova.enums.Course.JAVA;
import static com.plotnikova.enums.Course.SPRING;

public class StudentTest {

    private static Student student;

    @BeforeClass
    public static void createStudent() {
        student = new Student("Ivanov Ivan", "QE", JAVA, SPRING);
    }

    @Test
    public void shouldHasCorrespondingEndDay() {
        Assert.assertEquals(student.getProgramEnd().toLocalDate(), LocalDate.of(2021,9,2));
    }

    @Test
    public void shouldHasCorrespondingEndTime() {
        Assert.assertEquals(student.getProgramEnd().toLocalTime(), LocalTime.of(18, 0));
    }
}
