package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class HttpResponseTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        HttpResponse object = mockFactory.getHttpResponse();
        Assert.assertEquals(object, new HttpResponse(object));
        Assert.assertEquals(-2003792283, object.hashCode());
        Assert.assertEquals(
                "HttpResponse{protocolVersion='1.1', status='200 OK', headers={Content-Type=[application/json]}, body='{\"result\":\"OK\",\"message\":\"Created new user\"}'}",
                object.toString());
    }

}