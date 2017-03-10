package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class HttpSchemaTest {

    private HttpSchema httpSchema;

    @Before
    public void setup() {
        httpSchema = new HttpSchema();
    }

    @Test
    public void defaultValues() {
        Assert.assertTrue(httpSchema.isGateway());
        Assert.assertEquals("", httpSchema.getBasePath());
    }

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        HttpSchema object = mockFactory.getHttpSchema();
        Assert.assertEquals(object, new HttpSchema(object));
        Assert.assertEquals(1392240645, object.hashCode());
        Assert.assertEquals(
                "HttpSchema{gateway=true, basePath='/1.0.0'}",
                object.toString());
    }

}