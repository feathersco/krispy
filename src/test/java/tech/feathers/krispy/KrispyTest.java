package tech.feathers.krispy;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class KrispyTest {
    @BeforeClass
    public static void beforeClass() {
        Krispy.init();
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void render_basicTemplate() {
        Krispy k = new Krispy();
        assertEquals("Hello, World!", k.render("src/test/resources/templates/example.vtl"));
    }
}
