package tech.feathers.krispy.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.NotImplementedException;

/**
 * Implementation of the AWS AppSync resolver mapping template utility methods for time related functionality.
 */
public class TimeUtilities {
    public static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm'Z'";

    /**
     * Time provider to be used by the current instance.
     */
    private TimeProvider timeProvider;

    public TimeUtilities() {
        this(new SystemTimeProvider());
    }

    /**
     * @param timeProvider Time provider to use for utility functions based on current time.
     */
    public TimeUtilities(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    /**
     * @return Current time in ISO 8601 standard format.
     */
    public String nowISO8601() {
        return nowFormatted(ISO8601_FORMAT);
    }

    /**
     * @return Current time in seconds from epoch.
     */
    public long nowEpochSeconds() {
        return timeProvider.nowMillis() / 1000;
    }

    /**
     * @return Current time in milliseconds from epoch.
     */
    public long nowEpochMilliSeconds() {
        return timeProvider.nowMillis();
    }

    /**
     * @param format Java Date format pattern to use.
     * @return String representation of the current time using the given format pattern.
     */
    public String nowFormatted(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date(timeProvider.nowMillis());
        return sdf.format(date);
    }

    public String nowFormatted(String format, String timezone) { throw new NotImplementedException("Not yet implemented."); }

    /**
     * Parse the given timestamp using the given format pattern.
     * @param timestamp Timestamp to parse.
     * @param format    Format pattern string.
     * @return The milliseconds from epoch corresponding to the timestamp. Returns null if the timestamp cannot be parsed with the given pattern.
     */
    public Long parseFormattedToEpochMilliSeconds(String timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date dateTime = sdf.parse(timestamp);
            return dateTime.getTime();

        } catch (ParseException pe) {
            return null;
        }
    }

    public Long parseFormattedToEpochMilliSeconds(String timestamp, String format, String timezone) { throw new NotImplementedException("Not yet implemented."); }

    public Long parseISO8601ToEpochMilliSeconds(String timestamp) { throw new NotImplementedException("Not yet implemented."); }

    public long epochMilliSecondsToSeconds(long epochMs) { throw new NotImplementedException("Not yet implemented."); }

    public String epochMilliSecondsToISO8601(long epochMs) { throw new NotImplementedException("Not yet implemented."); }

    /**
     * Converts a given time in milliseconds from epoch, to a formatted timestamp.
     * @param epochMs   Time in milliseconds from epoch.
     * @param format    Format pattern to use.
     * @return Formatted timestamp of the given epoch time.
     */
    public String epochMilliSecondsToFormatted(long epochMs, String format) { 
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dateTime = new Date(epochMs);
        return sdf.format(dateTime);
    }

    public String epochMilliSecondsToFormatted(long epochMs, String format, String timezone) { throw new NotImplementedException("Not yet implemented."); }
}