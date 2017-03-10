package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class CallTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Call object = mockFactory.getCall();
        Assert.assertEquals(object, new Call(object));
        Assert.assertEquals(1824560448, object.hashCode());
        Assert.assertEquals(
                "Call{name='posts', version='1.2.0', action='list', params=[Param{name='X-Request-Token', value='ac3bd4b8-7da3-4c40-8661-746adfa55e0d', type='string', exists=false}, Param{name='user_id', value='123', type='integer', exists=false}], gateway='null', timeout=0}",
                object.toString());
    }

}