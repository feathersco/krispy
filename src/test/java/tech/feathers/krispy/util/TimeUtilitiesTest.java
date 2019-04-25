package tech.feathers.krispy.util;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import tech.feathers.krispy.Krispy;
import tech.feathers.krispy.util.FixedTimeProvider;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for time utility functions.
 * 
 * TODO: Should rewrite the tests to be tested without running it through Krispy.
 */
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
        try {
            assertEquals(timestamp, krispy.renderToString("src/test/resources/templates/util/time/nowISO8601.vtl"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void nowEpochMillisSeconds_called_fixedTime() {
        try {
            assertEquals(TEST_FIXED_TIME + "", krispy.renderToString("src/test/resources/templates/util/time/nowEpochMilliSeconds.vtl"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void nowEpochSeconds_called_fixedTime() {
        try {
            assertEquals((TEST_FIXED_TIME / 1000) + "", krispy.renderToString("src/test/resources/templates/util/time/nowEpochSeconds.vtl"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void nowFormatted_called_fixedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timestamp = sdf.format(new Date(TEST_FIXED_TIME));
        try {
            assertEquals(timestamp, krispy.renderToString("src/test/resources/templates/util/time/nowFormatted.vtl"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void epochMilliSecondsToFormatted_called_fixedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timestamp = sdf.format(new Date(TEST_FIXED_TIME));
        try {
            assertEquals(timestamp, krispy.renderToString("src/test/resources/templates/util/time/epochMilliSecondsToFormatted.vtl"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
}