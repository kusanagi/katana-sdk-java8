package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class HttpRequestTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        HttpRequest object = mockFactory.getHttpRequest();
        Assert.assertEquals(object, new HttpRequest(object));
        Assert.assertEquals(-110193202, object.hashCode());
        Assert.assertEquals(
                "HttpRequest{protocolVersion='1.1', method='POST', url='http://example.com/v1.0.0/users', queryParamsArray={name=[James], age=[32]}, postParamsArray={name=[Juan], age=[27]}, headers={Accept=[application/json]}, body='body', files=null}",
                object.toString());
    }

}