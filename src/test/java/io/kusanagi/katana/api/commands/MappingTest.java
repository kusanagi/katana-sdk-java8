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
public class MappingTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        Mapping object = mockFactory.getMapping("users", "0.1.0");
        Assert.assertEquals(object, new Mapping(object));
        Assert.assertEquals(-980562114, object.hashCode());
    }

}