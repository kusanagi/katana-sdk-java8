package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ActionHttpSchemaTest {

    private ActionHttpSchema actionHttpSchema;

    @Before
    public void setup() {
        actionHttpSchema = new ActionHttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(actionHttpSchema.isGateway());
        Assert.assertEquals("/", actionHttpSchema.getPath());
        Assert.assertEquals("get", actionHttpSchema.getMethod());
        Assert.assertEquals("query", actionHttpSchema.getInput());
        Assert.assertEquals("text/plain", actionHttpSchema.getBody()[0]);
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ActionHttpSchema object = mockFactory.getActionHttpSchama();
        Assert.assertEquals(object, new ActionHttpSchema(object));
        Assert.assertEquals(-589814501, object.hashCode());
        Assert.assertEquals(
                "ActionHttpSchema{gateway=true, path='/posts/{user_id}', method='get', input='path', body='[text/plain]'}",
                object.toString());
    }

}