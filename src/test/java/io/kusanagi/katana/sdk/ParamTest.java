package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ParamTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Param object = mockFactory.getParam();
        Assert.assertEquals(object, new Param(object));
        Assert.assertEquals(-323295288, object.hashCode());
        Assert.assertEquals(
                "Param{name='name', value='James', type='string', exists=false}",
                object.toString());
    }

}