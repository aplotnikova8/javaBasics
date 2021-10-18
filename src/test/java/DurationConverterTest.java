import com.plotnikova.converters.DurationConverter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class DurationConverterTest {

    @DataProvider(name = "data")
    public Object[][] dataProvider() {
        return new Object[][]{
                {48, "2 days"},
                {50, "2 days, 2 hours"},
                {12, "12 hours"}
        };
    }

    @Test(dataProvider = "data")
    public void checkConversionDurationToFullDays(int duration, String result) {
        Assert.assertEquals(DurationConverter.convertDurationToString(Duration.ofHours(duration)), result);
    }

}
