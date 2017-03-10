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
public class ResponseCommandPayloadTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ResponseCommandPayload object = mockFactory.getResponseCommandPayload();
        Assert.assertEquals(object, new ResponseCommandPayload(object));
        Assert.assertEquals(-978397492, object.hashCode());
    }

}