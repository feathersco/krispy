package tech.feathers.krispy.util;

import org.apache.commons.lang3.NotImplementedException; 

/**
 * Implementation of the AWS AppSync resolver mapping template utility methods for time related functionality.
 */
public class TimeUtilities {
    public String nowISO8601() { throw new NotImplementedException("Not yet implemented."); }

    public long nowEpochSeconds() { throw new NotImplementedException("Not yet implemented."); }

    public long nowEpochMilliSeconds() { throw new NotImplementedException("Not yet implemented."); }

    public String nowFormatted(String format) { throw new NotImplementedException("Not yet implemented."); }

    public String nowFormatted(String format, String timezone) { throw new NotImplementedException("Not yet implemented."); }

    public Long parseFormattedToEpochMilliSeconds(String timestamp, String format) { throw new NotImplementedException("Not yet implemented."); }

    public Long parseFormattedToEpochMilliSeconds(String timestamp, String format, String timezone) { throw new NotImplementedException("Not yet implemented."); }

    public Long parseISO8601ToEpochMilliSeconds(String timestamp) { throw new NotImplementedException("Not yet implemented."); }

    public long epochMilliSecondsToSeconds(long epochMs) { throw new NotImplementedException("Not yet implemented."); }

    public String epochMilliSecondsToISO8601(long epochMs) { throw new NotImplementedException("Not yet implemented."); }

    public String epochMilliSecondsToFormatted(long epochMs, String format) { throw new NotImplementedException("Not yet implemented."); }

    public String epochMilliSecondsToFormatted(long epochMs, String format, String timezone) { throw new NotImplementedException("Not yet implemented."); }
}