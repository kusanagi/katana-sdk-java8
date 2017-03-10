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
public class RequestCommandPayloadTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        RequestCommandPayload object = mockFactory.getRequestCommandPayload();
        Assert.assertEquals(object, new RequestCommandPayload(object));
        Assert.assertEquals(-1011198256, object.hashCode());
    }

}