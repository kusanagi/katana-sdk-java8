package io.kusanagi.katana.sdk;

import io.kusanagi.katana.utils.MockFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by juane on 2/11/17.
 */
public class ServiceTransactionTest {

    @Test
    public void equalsAndHashcode() {
        MockFactory mockFactory = new MockFactory();
        ServiceTransaction object = mockFactory.getServiceTransaction();
        Assert.assertEquals(object, new ServiceTransaction(object));
        Assert.assertEquals(-723744961, object.hashCode());
        Assert.assertEquals(
                "ServiceTransaction{name='users', version='1.0.0', action='create', caller='save', params=[Param{name='user_id', value='123', type='integer', exists=false}]}",
                object.toString());
    }

}