package io.kusanagi.katana.api.commands;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by juane on 2/11/17.
 */
@RunWith(JUnit4.class)
public class ActionCommandPayloadTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ActionCommandPayload object = mockFactory.getActionCommandPayload();
        Assert.assertEquals(object, new ActionCommandPayload(object));
        Assert.assertEquals(-190510998, object.hashCode());
    }

}