package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class FieldSchemaTest {

    private FieldSchema fieldSchema;

    @Before
    public void setup() {
        fieldSchema = new FieldSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(fieldSchema.isOptional());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        FieldSchema object = mockFactory.getFieldSchema();
        Assert.assertEquals(object, new FieldSchema(object));
        Assert.assertEquals(573298909, object.hashCode());
        Assert.assertEquals(
                "FieldSchema{name='id', type='integer', optional='false'}",
                object.toString());
    }

}