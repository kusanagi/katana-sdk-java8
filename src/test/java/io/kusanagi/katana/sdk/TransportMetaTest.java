package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class TransportMetaTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        TransportMeta object = mockFactory.getTransportMeta();
        Assert.assertEquals(object, new TransportMeta(object));
        Assert.assertEquals(798260320, object.hashCode());
        Assert.assertEquals(
                "TransportMeta{version='1.0.0', id='f1b27da9-240b-40e3-99dd-a567e4498ed7', datetime='2016-04-12T02:49:05.761', gateway=[12.34.56.78:1234, http://127.0.0.1:80], origin=[users, 1.0.0, list], level=1, fallback=[[users, 1.0.0, [create, update]]], properties={property=value}}",
                object.toString());
    }

}