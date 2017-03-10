package io.kusanagi.katana.sdk;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ErrorTest {

    private Error error;

    @Before
    public void setup() {
        this.error = new Error();
    }

    @Test
    public void defaultValues() {
        Assert.assertEquals("Unknown error", error.getMessage());
        Assert.assertEquals(0, error.getCode());
        Assert.assertEquals("500 Internal Server Error", error.getStatus());
    }

    @Test
    public void equalsAndHashcode() {
        Error error = new Error();
        Assert.assertEquals(error, new Error(error));
        Assert.assertEquals(-1348468619, error.hashCode());
        Assert.assertEquals(
                "Error{message='Unknown error', code='0', status='500 Internal Server Error'}",
                error.toString());
    }

}