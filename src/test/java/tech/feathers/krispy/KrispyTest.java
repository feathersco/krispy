package tech.feathers.krispy;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class KrispyTest {
    @BeforeClass
    public static void beforeClass() {
        Krispy.init();
    }

    @Test
    public void render_basicTemplate() {
        Krispy k = new Krispy();
        try {
            assertEquals("Hello, World!", k.renderToString("src/test/resources/templates/example.vtl"));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
}
