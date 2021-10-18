package com.plotnikova.converters;

import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class DurationConverter {

    public final int HOURS_IN_A_DAY = 24;

    public String convertDurationToString(Duration duration) {
        if (duration.toHours() % HOURS_IN_A_DAY == 0) {
            return String.format("%s days", (duration.toHours() / HOURS_IN_A_DAY));
        } else {
            if (duration.toHours() <= HOURS_IN_A_DAY) {
                return String.format("%s hours", duration.toHours());
            } else {
                int fullDays = (int) Math.floor(duration.toHours() / HOURS_IN_A_DAY);
                long hours = duration.minusDays(fullDays).toHours();
                return String.format("%1$s days, %2$s hours", fullDays, hours);
            }
        }
    }

}
