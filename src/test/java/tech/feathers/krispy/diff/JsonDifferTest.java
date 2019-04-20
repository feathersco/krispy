package tech.feathers.krispy.diff;

import static org.junit.Assert.*;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class JsonDifferTest {
    private JSONParser parser;

    @Before
    public void before() {
        parser = new JSONParser();
    }

    @Test
    public void diff_identical_noDiffs() {
        try {
            JSONObject a = (JSONObject) parser.parse("{\"a\": { \"a\": \"foo\", \"b\": \"bar\", \"c\": \"baz\" }, \"b\": { \"e\": \"kinda\" }, \"c\": { \"f\": \"sorta\" }}");
            JSONObject b = (JSONObject) parser.parse("{\"a\": { \"a\": \"foo\", \"b\": \"bar\", \"c\": \"baz\" }, \"b\": { \"e\": \"kinda\" }, \"c\": { \"f\": \"sorta\" }}");
            JsonDiffer differ = new JsonDiffer();
            List<JsonDiff> diffs = differ.diff(a, b);
            assertEquals(0, diffs.size());
        } catch (ParseException pe) {
            fail("Invalid JSON");
        }
    }

    @Test
    public void diff_oneModifiedOneRename_threeDiffs() {
        try {
            JSONObject a = (JSONObject) parser.parse("{ \"a\": \"foo\", \"b\": \"bar\", \"c\": \"baz\" }");
            JSONObject b = (JSONObject) parser.parse("{ \"a\": \"foo\", \"b\": \"qux\", \"d\": \"quux\" }");
            JsonDiffer differ = new JsonDiffer();
            List<JsonDiff> diffs = differ.diff(a, b);
            assertEquals(3, diffs.size());
        } catch (ParseException pe) {
            fail("Invalid JSON");
        }
    }

    @Test
    public void diff_nestedModify_oneDiff() {
        try {
            JSONObject a = (JSONObject) parser.parse("{\"a\": { \"a\": \"foo\", \"b\": \"bar\"}}");
            JSONObject b = (JSONObject) parser.parse("{\"a\": { \"a\": \"foo\", \"b\": \"qux\"}}");
            JsonDiffer differ = new JsonDiffer();
            List<JsonDiff> diffs = differ.diff(a, b);
            assertEquals(1, diffs.size());
        } catch (ParseException pe) {
            fail("Invalid JSON");
        }
    }
}
