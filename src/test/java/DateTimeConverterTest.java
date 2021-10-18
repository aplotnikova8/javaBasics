import com.plotnikova.converters.DateTimeConverter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeConverterTest {

    @Test
    public void shouldConvertDateTimeToString() {
        Assert.assertEquals(DateTimeConverter.convertDateTimeToString(LocalDate.of(2021,9,1).atTime(9,0)), "1 September 2021, Wednesday, 09:00");
    }

    @Test(expectedExceptions = DateTimeException.class)
    public void shouldNotConvertDateTimeToStringAndReturnNow() {
        String date = DateTimeConverter.convertDateTimeToString(LocalDate.of(2021,9,1).atTime(25,0));
        Assert.assertEquals(LocalDateTime.parse(date), LocalDateTime.now());
    }

}
