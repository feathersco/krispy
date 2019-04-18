package tech.feathers.krispy.util;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import tech.feathers.krispy.Krispy;
import tech.feathers.krispy.util.TimeProvider;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class TimeUtilitiesTest {
    private static final long TEST_FIXED_TIME = 1234567890L;

    private Krispy krispy;

    @BeforeClass
    public static void beforeClass() {
        Krispy.init();
    }

    @Before
    public void before() {
        krispy = new Krispy(
            new BaseUtilities(
                new TimeUtilities(
                    new FixedTimeProvider(TEST_FIXED_TIME)
                ),
                new DynamoDBUtilities()
            )
        );
    }

    @Test
    public void nowISO8601_called_fixedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(TimeUtilities.ISO8601_FORMAT);
        String timestamp = sdf.format(new Date(TEST_FIXED_TIME));
        assertEquals(timestamp, krispy.render("src/test/resources/templates/util/time/nowISO8601.vtl"));
    }

    @Test
    public void nowEpochMillisSeconds_called_fixedTime() {
        assertEquals(TEST_FIXED_TIME + "", krispy.render("src/test/resources/templates/util/time/nowEpochMilliSeconds.vtl"));
    }

    @Test
    public void nowEpochSeconds_called_fixedTime() {
        assertEquals((TEST_FIXED_TIME / 1000) + "", krispy.render("src/test/resources/templates/util/time/nowEpochSeconds.vtl"));
    }

    @Test
    public void nowFormatted_called_fixedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timestamp = sdf.format(new Date(TEST_FIXED_TIME));
        assertEquals(timestamp, krispy.render("src/test/resources/templates/util/time/nowFormatted.vtl"));
    }

    @Test
    public void epochMilliSecondsToFormatted_called_fixedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timestamp = sdf.format(new Date(TEST_FIXED_TIME));
        assertEquals(timestamp, krispy.render("src/test/resources/templates/util/time/epochMilliSecondsToFormatted.vtl"));
    }

    /**
     * Time provider to use for testing purposes.
     */
    public class FixedTimeProvider implements TimeProvider {
        private long fixedTimeMs;

        public FixedTimeProvider(long fixedTimeMs) {
            this.fixedTimeMs = fixedTimeMs;
        }

        public long nowMillis() {
            return fixedTimeMs;
        }
    }
}