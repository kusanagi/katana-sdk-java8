package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class RelationSchemaTest {

    private RelationSchema relationSchema;

    @Before
    public void setup() {
        relationSchema = new RelationSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertEquals("one", relationSchema.getType());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        RelationSchema object = mockFactory.getRelationSchema();
        Assert.assertEquals(object, new RelationSchema(object));
        Assert.assertEquals(-1826918592, object.hashCode());
        Assert.assertEquals(
                "RelationSchema{name='accounts', type='one'}",
                object.toString());
    }

}