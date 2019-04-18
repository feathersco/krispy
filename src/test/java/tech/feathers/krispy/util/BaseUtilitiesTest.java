package tech.feathers.krispy.util;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

import org.apache.velocity.exception.MethodInvocationException;
import tech.feathers.krispy.Krispy;
import tech.feathers.krispy.exception.UnauthorizedException;
import tech.feathers.krispy.exception.EvaluationException;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BaseUtilitiesTest {
    private Krispy krispy;

    @BeforeClass
    public static void beforeClass() {
        Krispy.init();
    }

    @Before
    public void before() {
        krispy = new Krispy();
    }

    @Test
    public void qr_allInputs_suppressesOutput() {
        assertEquals("", krispy.render("src/test/resources/templates/util/qr.vtl").trim());
    }

    @Test
    public void qiet_allInputs_suppressesOutput() {
        assertEquals("", krispy.render("src/test/resources/templates/util/qiet.vtl").trim());
    }

    @Test
    public void unauthorized_called_throws() {
        try {
            krispy.render("src/test/resources/templates/util/unauthorized.vtl");
        } catch (MethodInvocationException ex) {
            Throwable innerEx = ex.getCause();
            assertThat(innerEx, instanceOf(UnauthorizedException.class));
        }
    }

    @Test
    public void error_called_throws() {
        try {
            krispy.render("src/test/resources/templates/util/error.vtl");
        } catch (MethodInvocationException ex) {
            Throwable innerEx = ex.getCause();
            assertThat(innerEx, instanceOf(EvaluationException.class));
        }
    }
}