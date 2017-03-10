package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class FileTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        File object = mockFactory.getFile();
        Assert.assertEquals(object, new File(object));
        Assert.assertEquals(397391610, object.hashCode());
        Assert.assertEquals(
                "File{name='null', path='http://12.34.56.78:1234/files/ac3bd4b8-7da3-4c40-8661-746adfa55e0d', token='fb9an6c46be74s425010896fcbd99e2a', filename='smiley.jpg', size='1234567890', mime='image/jpeg', exists=false}",
                object.toString());
    }

}