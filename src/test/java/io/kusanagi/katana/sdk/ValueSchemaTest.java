package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ValueSchemaTest {

    private ValueSchema valueSchema;

    @Before
    public void setup() {
        valueSchema = new ValueSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertEquals("string", valueSchema.getType());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ValueSchema object = mockFactory.getValueSchema();
        Assert.assertEquals(object, new ValueSchema(object));
        Assert.assertEquals(492458141, object.hashCode());
        Assert.assertEquals(
                "ValueSchema{type='integer', value=321, items=null}",
                object.toString());
    }

}