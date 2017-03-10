package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ActionParamHttpSchemaTest {

    private ActionParamHttpSchema actionParamHttpSchema;

    @Before
    public void setup() {
        actionParamHttpSchema = new ActionParamHttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(actionParamHttpSchema.isGateway());
        Assert.assertEquals("query", actionParamHttpSchema.getInput());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ActionParamHttpSchema object = mockFactory.getActionParamHttpSchema();
        Assert.assertEquals(object, new ActionParamHttpSchema(object));
        Assert.assertEquals(-40693173, object.hashCode());
        Assert.assertEquals(
                "ActionParamHttpSchema{gateway=true, input='path', param='user_id'}",
                object.toString());
    }

}