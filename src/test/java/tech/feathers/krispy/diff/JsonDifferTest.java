package tech.feathers.krispy.diff;

import static org.junit.Assert.*;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class JsonDifferTest {

    @Test
    public void diff_identical_noDiffs() {
        Reader a = new StringReader("{\"a\": { \"a\": \"foo\", \"b\": \"bar\", \"c\": \"baz\" }, \"b\": { \"e\": \"kinda\" }, \"c\": { \"f\": \"sorta\" }}");
        Reader b = new StringReader("{\"a\": { \"a\": \"foo\", \"b\": \"bar\", \"c\": \"baz\" }, \"b\": { \"e\": \"kinda\" }, \"c\": { \"f\": \"sorta\" }}");
        JsonDiffer differ = new JsonDiffer();
        try {
            List<JsonDiff> diffs = differ.diff(a, b);
            assertNotNull(diffs);
            assertEquals(0, diffs.size());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void diff_oneModifiedOneRename_threeDiffs() {
        Reader a = new StringReader("{ \"a\": \"foo\", \"b\": \"bar\", \"c\": \"baz\" }");
        Reader b = new StringReader("{ \"a\": \"foo\", \"b\": \"qux\", \"d\": \"quux\" }");
        JsonDiffer differ = new JsonDiffer();
        try {
            List<JsonDiff> diffs = differ.diff(a, b);
            assertNotNull(diffs);
            assertEquals(3, diffs.size());
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void diff_nestedModify_oneDiff() {
        Reader a = new StringReader("{\"a\": { \"a\": \"foo\", \"b\": \"bar\"}}");
        Reader b = new StringReader("{\"a\": { \"a\": \"foo\", \"b\": \"qux\"}}");
        JsonDiffer differ = new JsonDiffer();
        try {
            List<JsonDiff> diffs = differ.diff(a, b);
            assertNotNull(diffs);
            assertEquals(1, diffs.size());
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}
