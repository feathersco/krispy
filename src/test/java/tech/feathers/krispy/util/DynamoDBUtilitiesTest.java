package tech.feathers.krispy.util;

import static org.junit.Assert.*;

import tech.feathers.krispy.Krispy;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

public class DynamoDBUtilitiesTest {
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
    public void toDynamoDBJson_string_serializeProperly() {
        assertEquals("{\"S\": \"test\"}", krispy.render("src/test/resources/templates/util/dynamodb/toStringJson.vtl").trim());
    }
}