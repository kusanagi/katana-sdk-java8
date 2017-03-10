package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ObjectFieldSchemaTest {

    private ObjectFieldSchema objectFieldSchema;

    @Before
    public void setup() {
        objectFieldSchema = new ObjectFieldSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertFalse(objectFieldSchema.isOptional());
        Assert.assertNotEquals(null, objectFieldSchema.getField());
        Assert.assertNotEquals(null, objectFieldSchema.getFields());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ObjectFieldSchema object = mockFactory.getObjectFieldSchema();
        Assert.assertEquals(object, new ObjectFieldSchema(object));
        Assert.assertEquals(964088491, object.hashCode());
        Assert.assertEquals(
                "ObjectFieldSchema{name='contact', optional=false, field=[FieldSchema{name='id', type='integer', optional='false'}, FieldSchema{name='email', type='null', optional='false'}, FieldSchema{name='location', type='object', optional='false'}], fields=[]}",
                object.toString());
    }

}